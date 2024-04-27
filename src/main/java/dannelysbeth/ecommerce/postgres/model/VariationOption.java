package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

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

}
