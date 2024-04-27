package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.repository.CartItemRepository;
import dannelysbeth.ecommerce.postgres.repository.CartRepository;
import dannelysbeth.ecommerce.postgres.service.definition.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartByUser(User user) {
        Cart cart = cartRepository.getByUser_Username(user.getUsername());
        if (cart == null)
            cart = cartRepository.save(Cart.builder()
                            .user(user)
                    .build());
        return cart;
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void addItemToCart(Cart cart, CartItem cartItem) {
        Set<CartItem> cartItems = cart.getCartItems();
        if(cartItems != null) {
            for (CartItem item : cartItems) {
                if (Objects.equals(item.getProductItem().getId(), cartItem.getProductItem().getId())) {
                    int quantity = item.getQuantity();
                    item.setQuantity(++quantity);
                    cartItemRepository.save(item);
                    return;
                }
            }
        }
        cartItemRepository.save(cartItem);
    }
}
