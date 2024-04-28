package dannelysbeth.ecommerce.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.AddressMapper;
import dannelysbeth.ecommerce.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.model.Country;
import dannelysbeth.ecommerce.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.ecommerce.postgres.model.User;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public Address tranformRequestToAddress(AddressRequest request, User user, Country country) {
        return Address.builder()
                .country(country)
                .city(request.getCity())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .streetNumber(request.getStreetNumber())
                .postalCode(request.getPostalCode())
                .user(user)
                .build();
    }

    @Override
    public AddressResponse transformAddressToResponse(Address address) {
        return AddressResponse.builder()
                .isDefault(address.isDefault())
                .country(address.getCountry().getCountry())
                .city(address.getCity())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .streetNumber(address.getStreetNumber())
                .postalCode(address.getPostalCode())
                .build();
    }
}
