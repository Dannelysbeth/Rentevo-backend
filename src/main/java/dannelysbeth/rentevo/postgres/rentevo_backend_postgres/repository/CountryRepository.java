package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {
    Optional<Country> getCountryByCountryIgnoreCase(String country);
}
