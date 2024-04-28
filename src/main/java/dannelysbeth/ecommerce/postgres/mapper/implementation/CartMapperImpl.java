package dannelysbeth.ecommerce.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.CartMapper;
import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.DTO.response.CartItemResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.CartResponse;
import dannelysbeth.ecommerce.postgres.model.OrderItem;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartMapperImpl implements CartMapper {
    public static Set<CartItemResponse> getCartItemResponsesForOrder(Set<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem ->
                CartItemResponse.builder()
                        .name(orderItem.getProductItem().getProduct().getName())
                        .featureSet(ProductMapperImpl.getFeaturesFromVariation(orderItem.getProductItem().getVariationOptions()))
                        .price(orderItem.getProductItem().getPrice())
                        .category(orderItem.getProductItem().getProduct().getCategory().getName())
                        .productCode(orderItem.getProductItem().getProduct().getId())
                        .quantity(orderItem.getQty())
                        .description(orderItem.getProductItem().getProduct().getDescription())
                        .build()
        ).collect(Collectors.toSet());
    }

    @Override
    public CartItem getCartItemFromProductItem(ProductItem productItem, Cart cart) {
        return CartItem.builder()
                .cart(cart)
                .productItem(productItem)
                .quantity(1)
                .build();
    }

    @Override
    public CartResponse transformToCartResponse(Cart cart) {
        return CartResponse.builder()
                .username(cart.getUser().getUsername())
                .items(getCartItemResponses(cart.getCartItems()))
                .total(cart.getTotal())
                .build();
    }

    private Set<CartItemResponse> getCartItemResponses(Set<CartItem> cartItems) {
        return cartItems.stream().map(cartItem ->
                CartItemResponse.builder()
                        .name(cartItem.getProductItem().getProduct().getName())
                        .featureSet(ProductMapperImpl.getFeaturesFromVariation(cartItem.getProductItem().getVariationOptions()))
                        .price(cartItem.getProductItem().getPrice())
                        .category(cartItem.getProductItem().getProduct().getCategory().getName())
                        .productCode(cartItem.getProductItem().getProduct().getId())
                        .quantity(cartItem.getQuantity())
                        .description(cartItem.getProductItem().getProduct().getDescription())
                        .build()
        ).collect(Collectors.toSet());
    }
}
