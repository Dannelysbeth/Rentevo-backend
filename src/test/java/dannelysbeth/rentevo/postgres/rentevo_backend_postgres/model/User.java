package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;

}
