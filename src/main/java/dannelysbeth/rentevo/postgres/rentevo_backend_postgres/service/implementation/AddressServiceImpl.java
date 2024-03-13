package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.implementation;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository.AddressRepository;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.AddressSpecification.cityStartsWith;
import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.AddressSpecification.countryStartsWith;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Set<Address> getAddressByUser(User user, String cityStartsWith, String countryStartsWith) {
        Specification<Address> filters = Specification.where(cityStartsWith == null ? null : cityStartsWith(cityStartsWith))
                .and(countryStartsWith == null ? null : countryStartsWith(countryStartsWith));
        return new HashSet<>(addressRepository.findAllByUser(user, filters));
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Long id, Address newAddress) {
        addressRepository.updateById(id, newAddress);
    }
}
