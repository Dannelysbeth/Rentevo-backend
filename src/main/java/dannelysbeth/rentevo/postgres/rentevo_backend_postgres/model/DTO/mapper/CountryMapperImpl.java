package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.mapper;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country toDto(Country country) {
        if ( country == null ) {
            return null;
        }

        Country countryDto = new Country();

        countryDto.setCountryName( country.getCountryName() );

        return countryDto;
    }
}