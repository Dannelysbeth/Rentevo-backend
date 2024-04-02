package dannelysbeth.rentevo.rentevo_backend.postgres.filters;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class UserSpecification {
    public static Specification<User> startsWithLastname(String startsWith) {
        return (root, query, builder) -> builder.like(root.get("lastname"), "%" + startsWith + "%");
    }

    public static Specification<User> startsWithFirstname(String code) {
        return (root, query, builder) -> builder.like(root.get("firstname"), "%" + code + "%");
    }
}
