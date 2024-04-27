package dannelysbeth.ecommerce.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.ProductItemResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.ProductResponse;
import dannelysbeth.ecommerce.postgres.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Set<ProductItem> transformFromRequest(List<ProductRequest> requests) {
        return requests.stream().map(req -> ProductItem.builder()
                .product(Product.builder()
                        .id(req.getProductCode())
                        .category(getCategory(req.getCategory()))
                        .description(req.getDescription())
                        .price(req.getPrice())
                        .name(req.getName())
                        .build())
                .sku(req.getSKU())
                .variationOptions(getVariationOptionsFromFeatures(req.getFeatures()))
                .quantityInStock(req.getQuantityInStock())
                .price(req.getPrice())
                .build()).collect(Collectors.toSet());
    }

    @Override
    public Set<ProductResponse> transformToProductResponse(Set<Product> products) {
        return products.stream().map(product ->
                ProductResponse.builder()
                        .productCode(product.getId())
                        .productItems(transformToProductItemResponse(product.getProductItems()))
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .category(product.getCategory().getName())
                        .name(product.getName())
                        .build()
        ).collect(Collectors.toSet());
    }

    @Override
    public Set<ProductItemResponse> transformToProductItemResponse(Set<ProductItem> productItems) {
        return productItems.stream().map(productItem ->
                ProductItemResponse.builder()
                        .featureSet(getFeaturesFromVariation(productItem.getVariationOptions()))
                        .SKU(productItem.getSku())
                        .quantityInStock(productItem.getQuantityInStock())
                        .price(productItem.getPrice())
                        .build()
        ).collect(Collectors.toSet());
    }

    public static Set<Feature> getFeaturesFromVariation(Set<VariationOption> variationOptions) {
        return variationOptions.stream().map(variationOption ->
                Feature.builder()
                        .parameter(variationOption.getVariation().getParameter())
                        .value(variationOption.getValue())
                        .build()
        ).collect(Collectors.toSet());
    }

    private Set<VariationOption> getVariationOptionsFromFeatures(List<Feature> features) {
        Set<VariationOption> variationOptionSet = new HashSet<>();
        for (Feature feature : features) {
            VariationOption build = VariationOption.builder()
                    .variation(Variation.builder()
                            .parameter(feature.getParameter())
                            .build())
                    .value(feature.getValue())
                    .build();
            variationOptionSet.add(build);
        }
        return variationOptionSet;
    }

    private ProductCategory getCategory(String categoryName) {
        return ProductCategory.builder()
                .name(categoryName)
                .build();
    }
}
