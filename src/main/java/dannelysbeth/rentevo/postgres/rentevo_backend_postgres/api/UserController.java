package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @GetMapping("/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username) {

        return ResponseEntity.ok()
                .body(userService.getByUsername(username));
    }

    @PreAuthorize("hasAnyAuthority('USER_ROLE', 'ADMIN_ROLE')")
    @GetMapping()
    ResponseEntity<User> getLoggedUser() {

        return ResponseEntity.ok()
                .body(userService.getLoggedUser());
    }

    @PreAuthorize("hasAnyAuthority('USER_ROLE', 'ADMIN_ROLE')")
    @GetMapping("/all")
    ResponseEntity<Set<User>> getAllUsers(@RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String lastname) {

        return ResponseEntity.ok()
                .body(userService.findAllUsers(firstname, lastname));
    }
}
