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
    public static final String QUANTITY_IN_STOCK = "quantityInStock";

    public static final String PRODUCT_ITEMS = "productItems";
    public static final String VARIATION = "variationOptions";

    public static final String NAME = "name";

    public static Specification<Product> filterBy(Double lowerPrice, Double higherPrice, Long minQuantity, List<String> category, List<String> colors, List<String> sizes) {
        return Specification
                .where(higherPrice == null ? null : hasLowerPriceThan(higherPrice))
                .and(lowerPrice == null ? null : hasGreaterPriceThan(lowerPrice))
                .and(minQuantity == null ? null : hasMinQuantity(minQuantity))
                .and(category == null ? null : hasCategories(category))
                .and(colors == null ? null : hasValues(colors))
                .and(sizes == null ? null : hasValues(sizes))
                ;
    }

    public static Specification<Product> hasLowerPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.greaterThanOrEqualTo(root.get(PRICE), price);
    }

    public static Specification<Product> hasGreaterPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.lessThanOrEqualTo(root.get(PRICE), price);
    }

    public static Specification<Product> hasMinQuantity(Long minQuantityInStock) {
        return (root, query, builder) ->
        {
            Join<ProductItem, Product> productItems = root.join(PRODUCT_ITEMS);
            return minQuantityInStock == null ? builder.conjunction() : builder.greaterThan(productItems.get(QUANTITY_IN_STOCK), minQuantityInStock);
        };
    }

    public static Specification<Product> hasCategories(List<String> categories) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, builder) -> {
            if (categories != null && !categories.isEmpty()) {
                for (String value : categories) {
                    predicates.add(builder.equal(root.get(CATEGORY).get(NAME), value));
                }
            }
            return categories == null ? builder.conjunction() : builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> hasValues(List<String> values) {
        return (root, query, builder) -> {
            Join<ProductItem, Product> productItems = root.join(PRODUCT_ITEMS, JoinType.INNER);
            Join<VariationOption, ProductItem> variations = productItems.join(VARIATION, JoinType.INNER);

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
