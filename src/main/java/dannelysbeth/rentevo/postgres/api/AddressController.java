package dannelysbeth.rentevo.postgres.api;

import dannelysbeth.rentevo.postgres.mapper.definition.AddressMapper;
import dannelysbeth.rentevo.postgres.model.DTO.request.AddressRequest;
import dannelysbeth.rentevo.postgres.model.DTO.response.AddressResponse;
import dannelysbeth.rentevo.postgres.model.User;
import dannelysbeth.rentevo.postgres.service.definition.AddressService;
import dannelysbeth.rentevo.postgres.service.definition.UserService;
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
    private final AddressService addressService;
    private final UserService userService;
    private final AddressMapper addressMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{username}")
    public ResponseEntity<Set<AddressResponse>> getAddresses(@RequestParam(required=false) String city,
                                                             @RequestParam(required=false) String country,
                                                             @RequestParam(required=false) String street,
                                                             @PathVariable String username) {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok()
                .body(addressService.getAddressByUser(user, city, country)
                        .stream()
                        .map(addressMapper::transformAddressToResponse)
                        .collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<String> saveOwnAddresses(@RequestBody AddressRequest addressRequest) {
        User loggedUser = userService.getLoggedUser();
        addressService.saveAddress(addressMapper.tranformRequestToAddress(addressRequest, loggedUser));
        return ResponseEntity.ok()
                .body("Address successfully saved");
    }
}
