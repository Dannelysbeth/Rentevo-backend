package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.Order;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;
import dannelysbeth.ecommerce.postgres.model.enums.OrderStatus;

import java.util.Set;

public interface OrderService {

    double getRepositoryResponseTime();

    Set<Order> getOrdersByUserAndStatus(String username, OrderStatus orderStatus);

    Set<Order> getOrdersByUser(String username);

    Order createOrder(User user);

    void updateOrder(Order order);

    void payForOrder(Long orderId, UserPaymentMethod userPaymentMethod);

    void deleteOrder(Order order);
}
