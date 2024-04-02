package dannelysbeth.rentevo.rentevo_backend.postgres.mapper.definition;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Address;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    Address tranformRequestToAddress(AddressRequest addressRequest, User user);

    AddressResponse transformAddressToResponse(Address address);
}
