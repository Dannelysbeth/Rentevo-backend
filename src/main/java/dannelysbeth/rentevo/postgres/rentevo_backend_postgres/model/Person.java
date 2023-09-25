package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Person {
    @Id
    private Long id;

    private String firstname;

    private String lastname;
}
