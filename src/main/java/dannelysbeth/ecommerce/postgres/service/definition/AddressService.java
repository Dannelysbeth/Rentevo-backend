package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.model.User;

import java.util.Set;

public interface AddressService {
    Set<Address> getAddressByUser(User user, String cityStartsWith, String countryStartsWith);

    void saveAddress(Address address);

    Address getAddressById(Long id);

}
