package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "products")
public class Product {
    @Id
    private String id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "category")
    private String category;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "price")
    private double price;
}
