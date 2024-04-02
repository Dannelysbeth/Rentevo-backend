package dannelysbeth.rentevo.rentevo_backend.service.implementation;

import dannelysbeth.rentevo.rentevo_backend.postgres.mapper.definition.CountryMapper;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;
import dannelysbeth.rentevo.rentevo_backend.postgres.repository.CountryRepository;
import dannelysbeth.rentevo.rentevo_backend.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static dannelysbeth.rentevo.rentevo_backend.postgres.filters.CountrySpecification.startsWithCode;
import static dannelysbeth.rentevo.rentevo_backend.postgres.filters.CountrySpecification.startsWithName;

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
