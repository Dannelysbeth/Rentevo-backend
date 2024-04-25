package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.repository.*;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            if (!this.productRepository.existsById(item.getProduct().getId())) {
                Product product = item.getProduct();
                ProductCategory category = saveProductCategory(product);
                product.setCategory(category);
                this.productRepository.save(item.getProduct());
            }
            item.setVariationOptions(saveVariationOptions(item.getVariationOptions()));
            this.productItemRepository.save(item);
        });
    }

    private Set<VariationOption> saveVariationOptions(Set<VariationOption> variationOptions) {
        return variationOptions.stream().map(variationOption -> {
            if (!this.variationOptionRepository.existsByVariation_ParameterAndValue(variationOption.getVariation().getParameter(), variationOption.getValue())) {
                if (!this.variationRepository.existsByParameter(variationOption.getVariation().getParameter())) {
                    Variation variation = this.variationRepository.save(variationOption.getVariation());
                    variationOption.setVariation(variation);
                }
                this.variationOptionRepository.save(variationOption);
            }
            return variationOptionRepository.getByVariation_ParameterAndValue(variationOption.getVariation().getParameter(), variationOption.getValue());
        }).collect(Collectors.toSet());
    }
    ProductCategory saveProductCategory(Product product) {
        ProductCategory productCategory = categoryRepository.getByName(product.getCategory().getName());
        if (productCategory == null) {
            return categoryRepository.save(product.getCategory());
        }
        return productCategory;
    }

}
