package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    User getUserById(@PathVariable String id) {
        return userService.getUserById(Long.getLong(id));
    }
}
