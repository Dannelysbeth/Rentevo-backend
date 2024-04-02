package dannelysbeth.rentevo.rentevo_backend.service.definition;


import dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.request.UserRequest;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    User getUserById(Long id);

    User getLoggedUser();

    User getByUsername(String username);

    Set<User> findAllUsers(String firstname, String lastname);

    void importUsers(Set<UserRequest> requests);
}
