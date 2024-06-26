package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.exception.AddressNotFoundException;
import dannelysbeth.ecommerce.postgres.filters.AddressSpecification;
import dannelysbeth.ecommerce.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.repository.AddressRepository;
import dannelysbeth.ecommerce.postgres.service.definition.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Set<Address> getAddressByUser(User user, String cityStartsWith, String countryStartsWith) {
        Specification<Address> filters = Specification.where(cityStartsWith == null ? null : AddressSpecification.cityStartsWith(cityStartsWith))
                .and(countryStartsWith == null ? null : AddressSpecification.countryStartsWith(countryStartsWith));
        return new HashSet<>(addressRepository.findAll(filters));
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

}
