package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="user_payment_methods")
public class UserPaymentMethod {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Setter
    @Column(name = "account_number")
    private String accountNumber;

    @Setter
    @Column(name = "account_number")
    private Date expiry_date;

    @Setter
    @Column(name = "account_number")
    private boolean isDefault;




}
