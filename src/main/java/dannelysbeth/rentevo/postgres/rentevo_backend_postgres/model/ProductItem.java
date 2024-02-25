package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "product_items")
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Setter
    @Column(name = "SKU")
    private String sku;

    @Setter
    @Column(name = "qty_in_stock")
    private int quantityInStock;

    @Setter
    @Column(name = "price")
    private double price;
}
