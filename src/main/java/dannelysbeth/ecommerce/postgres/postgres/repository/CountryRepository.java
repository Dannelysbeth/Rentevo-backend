package dannelysbeth.ecommerce.postgres.postgres.repository;

import dannelysbeth.ecommerce.postgres.postgres.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {
    Optional<Country> getCountryByCountryIgnoreCase(String country);
}
