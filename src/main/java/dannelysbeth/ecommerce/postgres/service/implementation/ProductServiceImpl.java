package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.filters.ProductSpecification;
import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.repository.*;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final VariationRepository variationRepository;
    private final VariationOptionRepository variationOptionRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public void importFromFile(MultipartFile file) {
        List<ProductRequest> productsReq = this.productMapper.readFromFile(file);
        Set<ProductItem> products = this.productMapper.transformFromRequest(productsReq);
        this.productItemRepository.saveAll(products);
    }

    @Override
    public void saveMany(Set<ProductItem> productItems) {
        productItems.forEach(item -> {
            Product product = saveProduct(item.getProduct());
            item.setProduct(product);
            item.setVariationOptions(saveVariationOptions(item.getVariationOptions()));
            this.productItemRepository.save(item);
        });
    }

    @Override
    public Set<Product> getProducts(Double priceStartsAt, Double priceEndsAt) {
        Specification<Product> filters = ProductSpecification.filterBy(priceStartsAt, priceEndsAt);
        return new HashSet<>(productRepository.findAll(filters));
    }

    private Product saveProduct(Product product) {
        if (!this.productRepository.existsById(product.getId())) {
            ProductCategory category = saveProductCategory(product.getCategory());
            product.setCategory(category);
            return this.productRepository.save(product);
        }
        return this.productRepository.getById(product.getId());
    }

    private Set<VariationOption> saveVariationOptions(Set<VariationOption> variationOptions) {
        return variationOptions.stream().map(variationOption -> {
            if (!this.variationOptionRepository.existsByVariation_ParameterAndValue(variationOption.getVariation().getParameter(), variationOption.getValue())) {
                Variation variation = this.variationRepository.getByParameter(variationOption.getVariation().getParameter());
                if (variation == null) {
                    variation = this.variationRepository.save(variationOption.getVariation());
                }
                variationOption.setVariation(variation);
                this.variationOptionRepository.save(variationOption);
            }
            return variationOptionRepository.getByVariation_ParameterAndValue(variationOption.getVariation().getParameter(), variationOption.getValue());
        }).collect(Collectors.toSet());
    }

    private ProductCategory saveProductCategory(ProductCategory productCategory) {
        ProductCategory foundCategory = categoryRepository.getByName(productCategory.getName());
        if (foundCategory == null) {
            return categoryRepository.save(productCategory);
        }
        return foundCategory;
    }

}
