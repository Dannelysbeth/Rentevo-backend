package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.mapper.definition.AddressMapper;
import dannelysbeth.ecommerce.postgres.model.Country;
import dannelysbeth.ecommerce.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.service.definition.AddressService;
import dannelysbeth.ecommerce.postgres.service.definition.CountryService;
import dannelysbeth.ecommerce.postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/address")
public class AddressController {
    private final CountryService countryService;
    private final AddressService addressService;
    private final UserService userService;
    private final AddressMapper addressMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @GetMapping("/{username}")
    public ResponseEntity<Set<AddressResponse>> getAddresses(@RequestParam(required = false) String city,
                                                             @RequestParam(required = false) String country,
                                                             @RequestParam(required = false) String street,
                                                             @PathVariable String username) {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok()
                .body(addressService.getAddressByUser(user, city, country)
                        .stream()
                        .map(addressMapper::transformAddressToResponse)
                        .collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @PostMapping("/create")
    public ResponseEntity<String> saveOwnAddresses(@RequestBody AddressRequest request) {
        Country country = countryService.getCountryByCode(request.getCountry());
        User loggedUser = userService.getLoggedUser();
        addressService.saveAddress(addressMapper.tranformRequestToAddress(request, loggedUser, country));
        return ResponseEntity.ok()
                .body("Address successfully saved");
    }
}
