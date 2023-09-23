package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String email;

    @ManyToMany
    private Set<Role> roles;
}
