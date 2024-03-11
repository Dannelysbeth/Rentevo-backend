package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;

import java.util.Set;

public interface CountryService {

    Set<Country> findAll(String startsWithName, String startsWithCode);

    void importCountry(Country country);

    void importCountries(Set<Country> countries);
}
