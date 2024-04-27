package dannelysbeth.ecommerce.postgres.filters;

import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.VariationOption;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class ProductSpecification {

    public static final String COLOR = "Color";
    public static final String MATERIAL = "Material";
    public static final String SIZE = "Size";
    public static final String PRICE = "price";
    public static final String CATEGORY = "category";

    public static final String VALUE = "value";

    public static Specification<Product> filterBy(Double lowerPrice, Double higherPrice, String color) {
        return Specification
                .where(hasLowerPriceThan(higherPrice))
                .and(hasGreaterPriceThan(lowerPrice))
                .and(hasColor(color));


    }

    public static Specification<Product> hasLowerPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.lessThan(root.get(PRICE), price);
    }

    public static Specification<Product> hasGreaterPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.lessThan(root.get(PRICE), price);
    }

    public static Specification<Product> hasMinQuantity(long minQuantityInStock) {
        return (root, query, builder) ->
        {
            Join<ProductItem, Product> productItems = root.join("productItems");
            return builder.greaterThan(productItems.get("quantityInStock"), minQuantityInStock);
        };
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, builder) ->
        {
            Join<ProductItem, Product> productItems = root.join("productItems");
            Join<VariationOption,ProductItem> variations = productItems.join("variationOptions");
            return builder.equal(variations.get(VALUE), color);
        };
    }

}
