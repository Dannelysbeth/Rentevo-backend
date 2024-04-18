package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.List;

import java.util.Set;

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

    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VariationOption> variationOptions;
}
