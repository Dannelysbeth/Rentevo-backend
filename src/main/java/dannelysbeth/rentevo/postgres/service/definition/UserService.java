package dannelysbeth.rentevo.postgres.service.definition;


import dannelysbeth.rentevo.postgres.model.DTO.request.UserRequest;
import dannelysbeth.rentevo.postgres.model.User;
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
