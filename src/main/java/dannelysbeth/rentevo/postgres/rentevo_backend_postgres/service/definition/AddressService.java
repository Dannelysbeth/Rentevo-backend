package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;

import java.util.Set;

public interface AddressService {
    Set<Address> getAddressByUser(User user, String cityStartsWith, String countryStartsWith);

    void saveAddress(Address address);

    void updateAddress(Long id, Address newAddress);
}
