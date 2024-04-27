package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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
    @Column(name = "parameter")
    private String parameter;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VariationOption> variationOptions;

    public void addVariationOption(VariationOption variationOption) {
        if (variationOptions == null) {
            this.variationOptions = new HashSet<>();
        }
        variationOptions.add(variationOption);
    }
}
