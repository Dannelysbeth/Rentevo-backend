package dannelysbeth.rentevo.postgres.mapper.implementation;

import dannelysbeth.rentevo.postgres.model.Country;
import dannelysbeth.rentevo.postgres.mapper.definition.CountryMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country toDto(Country country) {
        if ( country == null ) {
            return null;
        }

        Country countryDto = new Country();

        countryDto.setCountry( country.getCountry() );

        return countryDto;
    }
}