package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.CountryMapper;
import dannelysbeth.ecommerce.postgres.model.Country;
import dannelysbeth.ecommerce.postgres.repository.CountryRepository;
import dannelysbeth.ecommerce.postgres.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static dannelysbeth.ecommerce.postgres.filters.CountrySpecification.startsWithCode;
import static dannelysbeth.ecommerce.postgres.filters.CountrySpecification.startsWithName;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Set<Country> findAll(String country, String code) {
        Specification<Country> filters = Specification.where(country == null ? null : startsWithName(country))
                .and(code == null ? null : startsWithCode(code));
        return new HashSet<>(countryRepository.findAll(filters));
    }

    @Override
    public void importCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void importCountries(Set<Country> countries) {
        countryRepository.saveAll(countries);
    }
}
