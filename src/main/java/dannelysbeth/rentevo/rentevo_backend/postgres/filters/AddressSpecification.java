package dannelysbeth.rentevo.rentevo_backend.postgres.filters;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class AddressSpecification {
    public static Specification<Address> cityStartsWith(String city) {
        return (root, query, builder) -> builder.like(root.get("city"), "%" + city + "%");
    }

    public static Specification<Address> countryStartsWith(String country) {
        return (root, query, builder) -> builder.like(root.get("country"), "%" + country + "%");
    }
}
