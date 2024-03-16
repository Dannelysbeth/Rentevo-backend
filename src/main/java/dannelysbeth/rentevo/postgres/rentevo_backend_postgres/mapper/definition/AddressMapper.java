package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.definition;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.request.AddressRequest;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response.AddressResponse;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    Address tranformRequestToAddress(AddressRequest addressRequest, User user);

    AddressResponse transformAddressToResponse(Address address);
}
