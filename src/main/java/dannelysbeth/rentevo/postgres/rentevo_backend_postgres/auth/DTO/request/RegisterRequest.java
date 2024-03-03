package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.auth.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String username;
}
