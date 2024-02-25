package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Set<Promotion> promotionSet;

    @Setter
    @Column(name = "category_name")
    private String categoryName;

}
