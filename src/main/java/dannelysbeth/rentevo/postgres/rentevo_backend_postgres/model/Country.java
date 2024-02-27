package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "country_name")
    private String countryName;
}
