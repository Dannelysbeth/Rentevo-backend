package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "countries")
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private ShopOrder shopOrder;

    @Setter
    @Column(name = "qty")
    private int qty;

    @Setter
    @Column(name = "price")
    private double price;
}