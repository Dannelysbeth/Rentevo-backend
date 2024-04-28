package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.model.Country;
import dannelysbeth.ecommerce.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.ecommerce.postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    Address tranformRequestToAddress(AddressRequest addressRequest, User user, Country country);

    AddressResponse transformAddressToResponse(Address address);
}
