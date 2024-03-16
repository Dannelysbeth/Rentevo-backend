package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
    Set<Address> getAddressesByUser(User user);

//    <T> List<Address> findAll(User user, Specification<T> spec);
}
