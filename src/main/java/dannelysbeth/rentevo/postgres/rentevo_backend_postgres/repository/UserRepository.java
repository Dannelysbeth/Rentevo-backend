package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
