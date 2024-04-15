package dannelysbeth.ecommerce.postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_configuration",
            joinColumns = {@JoinColumn(name = "product_item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "variation_option_id", referencedColumnName = "id")})
    private Set<VariationOption> variationOptions;

}
