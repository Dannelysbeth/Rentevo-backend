package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.implementation;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.definition.AddressMapper;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.request.AddressRequest;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public Address tranformRequestToAddress(AddressRequest request) {
        return Address.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .streetNumber(request.getStreetNumber())
                .postalCode(request.getPostalCode())
                .build();
    }

    @Override
    public AddressResponse transformAddressToResponse(Address address) {
        return AddressResponse.builder()
                .user(address.getUser())
                .isDefault(address.isDefault())
                .country(address.getCountry())
                .city(address.getCity())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .build();
    }
}