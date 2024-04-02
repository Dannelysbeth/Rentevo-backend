package dannelysbeth.rentevo.rentevo_backend.postgres.model;

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
    @Column(name = "code")
    private String code;

    @Setter
    @Column(name = "country")
    private String country;
}
