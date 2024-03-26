package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String firstname;

    private String lastname;

    private String email;

    private String username;
}
