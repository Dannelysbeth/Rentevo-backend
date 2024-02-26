package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="shop_orders")
public class ShopOrder {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private UserPaymentMethod paymentMethod;

    @Setter
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "shipping_address")
    private Address shippingAddress;

    @Setter
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "shipping_method")
    private ShippingMethod shippingMethod;

    @Setter
    @Column(name = "order_date")
    private Date orderDate;

    @Setter
    @Column(name = "order_total")
    private double orderTotal;

    @Setter
    @Column(name = "order_status")
    private OrderStatus orderStatus;

}
