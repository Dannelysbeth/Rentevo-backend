package dannelysbeth.ecommerce.postgres.filters;

import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.VariationOption;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductSpecification {
    public static final String PRICE = "price";
    public static final String CATEGORY = "category";
    public static final String VALUE = "value";

    public static Specification<Product> filterBy(Double lowerPrice, Double higherPrice, Long minQuantity, List<String> category, List<String> colors, List<String> sizes) {
        return Specification
                .where(hasLowerPriceThan(higherPrice))
                .and(hasGreaterPriceThan(lowerPrice))
                .and(hasMinQuantity(minQuantity))
                .and(hasCategories(category))
                .and(hasValues(colors))
                .and(hasValues(sizes))
                ;
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

    public static Specification<Product> hasCategories(List<String> categories) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, builder) -> {
            for (String value : categories) {
                predicates.add(builder.equal(root.get(CATEGORY).get("name"), value));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> hasValues(List<String> values) {
        return (root, query, builder) -> {
            Join<ProductItem, Product> productItems = root.join("productItems", JoinType.INNER);
            Join<VariationOption, ProductItem> variations = productItems.join("variationOptions", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            if (values != null && !values.isEmpty()) {
                for (String value : values) {
                    predicates.add(builder.equal(variations.get(VALUE), value));
                }
            }
            return values == null ? builder.conjunction() : builder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
