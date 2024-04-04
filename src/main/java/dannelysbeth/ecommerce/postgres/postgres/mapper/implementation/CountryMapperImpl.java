package dannelysbeth.ecommerce.postgres.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.postgres.mapper.definition.CountryMapper;
import dannelysbeth.ecommerce.postgres.postgres.model.Country;
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