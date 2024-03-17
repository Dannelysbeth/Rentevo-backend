package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable String id) {

        return ResponseEntity.ok()
                .body(userService.getUserById(Long.getLong(id)));
    }

    @GetMapping()
    ResponseEntity<User> getLoggedUser() {

        return ResponseEntity.ok()
                .body(userService.getLoggedUser());
    }
}
