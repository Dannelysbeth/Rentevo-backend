package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition;


import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    User getUserById(Long id);

    User getLoggedUser();

    User getByUsername(String username);

    Set<User> findAllUsers(String firstname, String lastname);
}
