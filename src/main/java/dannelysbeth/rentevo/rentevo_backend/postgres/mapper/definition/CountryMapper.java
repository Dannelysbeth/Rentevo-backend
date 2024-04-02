package dannelysbeth.rentevo.rentevo_backend.postgres.mapper.definition;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CountryMapper {
    Country toDto(Country country);
}
