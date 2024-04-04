package dannelysbeth.ecommerce.postgres.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parentCategory;

    @Setter
    @Column(name = "category_name")
    private String categoryName;


    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "promotion_id")
    @JoinTable(name = "promotion_categories",
            joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")})
    private Set<Promotion> promotionSet;

}
