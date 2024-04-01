package dannelysbeth.rentevo.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "discount_rate", nullable = false)
    private double discountRate;

    @Setter
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Setter
    @Column(name = "end_date", nullable = false)
    private Date endDate;


    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "promotion_categories",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<ProductCategory> productCategories;
}
