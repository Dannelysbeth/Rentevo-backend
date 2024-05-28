package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.exception.NotEnoughProductException;
import dannelysbeth.ecommerce.postgres.filters.ProductSpecification;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.repository.*;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

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

    private StopWatch watch;

    @Override
    public double getRepositoryResponseTime() {
        return this.watch.getTotalTimeMillis();
    }


    @Override
    public void saveMany(Set<ProductItem> productItems) {
        this.watch = new StopWatch();
        watch.start();
        productItems.forEach(item -> {
            Product product = saveProduct(item.getProduct());
            item.setProduct(product);
            item.setVariationOptions(saveVariationOptions(item.getVariationOptions()));
            this.productItemRepository.save(item);
        });
        this.watch.stop();
    }

    @Override
    public Set<Product> getProducts(Double priceStartsAt, Double priceEndsAt, Long quantity, List<String> category, List<String> color, List<String> size) {
        this.watch = new StopWatch();
        watch.start();
        Specification<Product> filters = ProductSpecification.filterBy(priceStartsAt, priceEndsAt, quantity, category, color, size);
        List<Product> products = productRepository.findAll(filters);
        this.watch.stop();
        return new HashSet<>(products);
    }

    @Override
    public ProductItem getProductItemById(Long id) {
        this.watch = new StopWatch();
        watch.start();
        ProductItem productItem = productItemRepository.getById(id);
        this.watch.stop();
        return productItem;
    }

    @Override
    public void decreaseProductItems(Order order) {
        Set<OrderItem> orderItems = order.getOrderItems();
        Set<ProductItem> productItems = new HashSet<>();
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                ProductItem item = orderItem.getProductItem();
                long newQuantity = item.getQuantityInStock() - orderItem.getQty();
                if (newQuantity < 0) {
                    throw new NotEnoughProductException();
                }
                item.setQuantityInStock(newQuantity);
                productItems.add(item);
            }
            this.watch = new StopWatch();
            watch.start();
            productItemRepository.saveAll(productItems);
            this.watch.stop();
        }
    }

    @Override
    public void deleteAll() {
        productItemRepository.deleteAll();
        productRepository.deleteAll();
    }


    private Product saveProduct(Product product) {
        if (!this.productRepository.existsById(product.getId())) {
            ProductCategory category = saveProductCategory(product.getCategory());
            product.setCategory(category);
            return this.productRepository.save(product);
        }
        return this.productRepository.findById(product.getId()).orElse(null);
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
