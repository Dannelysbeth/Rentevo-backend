package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Address;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.AddressService;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/address")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<Set<Address>> getAddresses(@RequestParam(required=false) String city,
                                                     @RequestParam(required=false) String country,
                                                     @RequestParam(required=false) String street,
                                                     @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok()
                .body(addressService.getAddressByUser(user, city, country));
    }
}
