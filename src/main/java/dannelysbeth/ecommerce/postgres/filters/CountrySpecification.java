package dannelysbeth.ecommerce.postgres.filters;

import dannelysbeth.ecommerce.postgres.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class CountrySpecification {
    public static Specification<Country> startsWithName(String startsWith) {
        return (root, query, builder) -> builder.like(root.get("country"), "%" + startsWith + "%");
    }

    public static Specification<Country> startsWithCode(String code) {
        return (root, query, builder) -> builder.like(root.get("code"), "%" + code + "%");
    }
}
