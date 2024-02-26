package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.util.Lazy;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="user_reviews")
public class UserReview {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_product_id")
    private OrderLine orderLine;

    @Setter
    @Column(name = "rating_value")
    private double ratingValue;

    @Setter
    @Column(name = "comment")
    private String comment;
}
