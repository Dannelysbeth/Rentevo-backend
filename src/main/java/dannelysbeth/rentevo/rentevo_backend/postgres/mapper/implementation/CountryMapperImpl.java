package dannelysbeth.rentevo.rentevo_backend.postgres.mapper.implementation;

import dannelysbeth.rentevo.rentevo_backend.postgres.mapper.definition.CountryMapper;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country toDto(Country country) {
        if (country == null) {
            return null;
        }

        Country countryDto = new Country();

        countryDto.setCountry(country.getCountry());

        return countryDto;
    }
}