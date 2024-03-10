package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.implementation;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.mapper.CountryMapper;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository.CountryRepository;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.CountrySpecification.startsWithCode;
import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.CountrySpecification.startsWithName;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Set<Country> findAll(String name, String code) {
        Specification<Country> filters = Specification.where(StringUtils.isEmpty(name) ? null : startsWithName(name))
                .and(StringUtils.isEmpty(code) ? null : startsWithCode(code));
        return countryRepository.findAll(filters)
                .stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toSet());
    }
}
