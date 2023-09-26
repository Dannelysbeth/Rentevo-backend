package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
