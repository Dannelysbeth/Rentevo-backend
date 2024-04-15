package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "categories")
public class ProductCategory {
    @Id
    private Long id;

    @Setter
    @Column(name = "category")
    private String categoryName;

}
