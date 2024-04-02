package dannelysbeth.rentevo.rentevo_backend.service.definition;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;

import java.util.Set;

public interface CountryService {

    Set<Country> findAll(String startsWithName, String startsWithCode);

    void importCountry(Country country);

    void importCountries(Set<Country> countries);
}
