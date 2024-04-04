package dannelysbeth.ecommerce.postgres.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.ecommerce.postgres.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.ecommerce.postgres.postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    Address tranformRequestToAddress(AddressRequest addressRequest, User user);

    AddressResponse transformAddressToResponse(Address address);
}
