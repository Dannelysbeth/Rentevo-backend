package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "shipping_methods")
public class ShippingMethod {
    @Id
    private String id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "price")
    private double price;
}
