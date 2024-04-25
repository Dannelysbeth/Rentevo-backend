package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "categories")
public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "category")
    private String name;

}
