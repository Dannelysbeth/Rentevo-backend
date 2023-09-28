package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition;


import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(Long id);
}
