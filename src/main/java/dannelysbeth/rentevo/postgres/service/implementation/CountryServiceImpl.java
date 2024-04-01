package dannelysbeth.rentevo.postgres.service.implementation;

import dannelysbeth.rentevo.postgres.model.Country;
import dannelysbeth.rentevo.postgres.mapper.definition.CountryMapper;
import dannelysbeth.rentevo.postgres.repository.CountryRepository;
import dannelysbeth.rentevo.postgres.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static dannelysbeth.rentevo.postgres.filters.CountrySpecification.startsWithCode;
import static dannelysbeth.rentevo.postgres.filters.CountrySpecification.startsWithName;

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
