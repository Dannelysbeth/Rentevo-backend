package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.enums.OrderStatus;
import dannelysbeth.ecommerce.postgres.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> getByUser_UsernameAndOrderStatus(String username, OrderStatus orderStatus);

    Set<Order> getByUser_Username(String username);

}

