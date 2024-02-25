package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(name = "unit_number")
    private int unitNumber;

    @Setter
    @Column(name = "street_number")
    private int streetNumber;

    @Setter
    @Column(name = "address_line1")
    private String addressLine1;

    @Setter
    @Column(name = "address_line2")
    private String addressLine2;

    @Setter
    @Column(name = "city")
    private String city;

    @Setter
    @Column(name = "postal_code")
    private String postalCode;

    @Setter
    @Column(name = "is_default")
    private boolean isDefault;






}
