package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CountryMapper {
    Country toDto(Country country);
}
