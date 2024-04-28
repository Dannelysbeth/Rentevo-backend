package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {

}
