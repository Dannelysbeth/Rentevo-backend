package dannelysbeth.rentevo.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "variation_options")
public class VariationOption {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id")
    private Variation variation;

    @Setter
    @Column(name = "value")
    private String value;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_configuration",
            joinColumns = {@JoinColumn(name = "variation_option_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_item_id", referencedColumnName = "id")})
    private Set<VariationOption> variationOptions;
}
