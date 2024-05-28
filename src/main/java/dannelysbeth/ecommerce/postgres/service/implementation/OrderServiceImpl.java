package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.OrderMapper;
import dannelysbeth.ecommerce.postgres.model.Order;
import dannelysbeth.ecommerce.postgres.model.OrderItem;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;
import dannelysbeth.ecommerce.postgres.model.enums.OrderStatus;
import dannelysbeth.ecommerce.postgres.repository.OrderItemRepository;
import dannelysbeth.ecommerce.postgres.repository.OrderRepository;
import dannelysbeth.ecommerce.postgres.service.definition.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final OrderMapper orderMapper;

    private StopWatch watch;

    @Override
    public double getRepositoryResponseTime() {
        return this.watch.getTotalTimeMillis();
    }

    @Override
    public Set<Order> getOrdersByUserAndStatus(String username, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public Set<Order> getOrdersByUser(String username) {

        return orderRepository.getByUser_Username(username);
    }

    @Override
    public Order createOrder(User user) {
        Order order = orderMapper.initOrder(user);
        watch = new StopWatch();
        watch.start();
        order = orderRepository.save(order);
        watch.stop();
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        saveOrderItems(order.getOrderItems());
        watch = new StopWatch();
        watch.start();
        orderRepository.save(order);
        watch.stop();


    }

    private void saveOrderItems(Set<OrderItem> orderItems) {
        watch = new StopWatch();
        watch.start();
        orderItemRepository.saveAll(orderItems);
        watch.stop();
    }

    @Override
    public void payForOrder(Long orderId, UserPaymentMethod userPaymentMethod) {


    }

    @Override
    public void deleteOrder(Order order) {
        watch = new StopWatch();
        watch.start();
        orderRepository.delete(order);
        watch.stop();
    }
}
