package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.definition.AddressMapper;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.request.AddressRequest;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response.AddressResponse;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.AddressService;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/address")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;
    private final AddressMapper addressMapper;

    @GetMapping("/{username}")
    public ResponseEntity<Set<AddressResponse>> getAddresses(@RequestParam(required=false) String city,
                                                             @RequestParam(required=false) String country,
                                                             @RequestParam(required=false) String street,
                                                             @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok()
                .body(addressService.getAddressByUser(user, city, country)
                        .stream()
                        .map(addressMapper::transformAddressToResponse)
                        .collect(Collectors.toSet()));
    }

    @PostMapping()
    public ResponseEntity<String> saveOwnAddresses(@RequestBody AddressRequest addressRequest) {
        addressService.saveAddress(addressMapper.tranformRequestToAddress(addressRequest));
        return ResponseEntity.ok()
                .body("Address successfully saved");
    }
}
