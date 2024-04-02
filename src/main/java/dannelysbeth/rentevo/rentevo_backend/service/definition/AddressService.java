package dannelysbeth.rentevo.rentevo_backend.service.definition;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Address;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.User;

import java.util.Set;

public interface AddressService {
    Set<Address> getAddressByUser(User user, String cityStartsWith, String countryStartsWith);

    void saveAddress(Address address);

    void updateAddress(Long id, Address newAddress);
}
