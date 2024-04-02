package dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.request;

import lombok.Data;

@Data
public class UserRequest {
    private String firstname;

    private String lastname;

    private String email;

    private String username;

}
