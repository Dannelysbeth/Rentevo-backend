package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Set<CartItem> getByCart_Id(Long cartId);
}
