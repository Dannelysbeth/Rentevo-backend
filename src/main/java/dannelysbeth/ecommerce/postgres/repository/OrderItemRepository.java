package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.Order;
import dannelysbeth.ecommerce.postgres.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Set<OrderItem> getByShopOrder(Order order);
}
