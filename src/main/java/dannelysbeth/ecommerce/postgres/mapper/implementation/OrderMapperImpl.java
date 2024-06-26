package dannelysbeth.ecommerce.postgres.mapper.implementation;


import dannelysbeth.ecommerce.postgres.mapper.definition.AddressMapper;
import dannelysbeth.ecommerce.postgres.mapper.definition.OrderMapper;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.model.DTO.request.OrderRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.OrderResponse;
import dannelysbeth.ecommerce.postgres.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {
    private final AddressMapper addressMapper;

    @Override
    public Order initOrder(User user) {
        return Order.builder()
                .orderDate(new Date())
                .orderStatus(OrderStatus.CREATED)
                .user(user)
                .build();
    }

    @Override
    public Order updateOrderFromRequest(Order order, OrderRequest request, Cart cart, Address shippingAddress, ShippingMethod shippingMethod) {
        Set<OrderItem> orderItems = getItemFromCart(order, cart.getCartItems());
        return Order.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus() == null ? OrderStatus.CREATED : order.getOrderStatus())
                .orderDate(order.getOrderDate() == null ? new Date() : order.getOrderDate())
                .shippingMethod(shippingMethod)
                .shippingAddress(shippingAddress)
                .orderItems(orderItems)
                .user(order.getUser())
                .orderTotal(countTotalPrice(orderItems))
                .build();
    }

    @Override
    public Set<OrderResponse> transformToOrderResponse(Set<Order> orders) {
        if (orders == null)
            return null;
        return orders.stream().map(order ->
                        OrderResponse.builder()
                                .username(order.getUser().getUsername())
                                .items(CartMapperImpl.getCartItemResponsesForOrder(order.getOrderItems()))
                                .total(order.getOrderTotal())
                                .shippingAddress(addressMapper.transformAddressToResponse(order.getShippingAddress()))
                                .orderDate(order.getOrderDate())
                                .orderStatus(order.getOrderStatus().getDisplayName())
                                .shippingMethod(order.getShippingMethod().getName())
                                .build())
                .collect(Collectors.toSet());
    }

    private Set<OrderItem> getItemFromCart(Order order, Set<CartItem> cartItems) {
        return cartItems.stream().map(cartItem ->
                OrderItem.builder()
                        .shopOrder(order)
                        .qty(cartItem.getQuantity())
                        .productItem(cartItem.getProductItem())
                        .price(cartItem.getProductItem().getPrice())
                        .build()
        ).collect(Collectors.toSet());
    }

    private double countTotalPrice(Set<OrderItem> orderItems) {
        double sum = 0;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                sum += orderItem.getPrice();
            }
        }
        return sum;
    }


}
