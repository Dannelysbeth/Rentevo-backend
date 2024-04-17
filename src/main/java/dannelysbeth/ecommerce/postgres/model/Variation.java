package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "variations")
public class Variation {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "name")
    private String parameter;
}
