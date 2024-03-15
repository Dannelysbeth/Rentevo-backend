package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.definition;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CountryMapper {
    Country toDto(Country country);
}
