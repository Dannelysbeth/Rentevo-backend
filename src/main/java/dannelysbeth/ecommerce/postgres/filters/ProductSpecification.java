package dannelysbeth.ecommerce.postgres.filters;

import dannelysbeth.ecommerce.postgres.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class ProductSpecification {

    public static final String COLOR = "Color";
    public static final String MATERIAL = "Material";
    public static final String SIZE = "Size";
    public static final String PRICE = "price";
    public static final String CATEGORY = "category";

    public static Specification<Product> filterBy(Double lowerPrice, Double higherPrice) {
        return Specification
                .where(hasLowerPriceThan(Double.valueOf(higherPrice)))
                .and(hasGreaterPriceThan(Double.valueOf(lowerPrice)));

    }

    public static Specification<Product> hasLowerPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.lessThan(root.get(PRICE), price);
    }

    public static Specification<Product> hasGreaterPriceThan(Double price) {
        return (root, query, builder) -> price == null ? builder.conjunction() : builder.greaterThan(root.get(PRICE), price);
    }

}
