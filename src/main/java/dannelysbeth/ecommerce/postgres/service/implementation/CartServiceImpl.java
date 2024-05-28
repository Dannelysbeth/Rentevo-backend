package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.repository.CartItemRepository;
import dannelysbeth.ecommerce.postgres.repository.CartRepository;
import dannelysbeth.ecommerce.postgres.service.definition.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private StopWatch watch ;

    @Override
    public double getRepositoryResponseTime() {
        return this.watch.getTotalTimeMillis();
    }

    @Override
    public Cart getCartByUser(User user) {
        watch = new StopWatch();
        this.watch.start();
        Cart cart = cartRepository.getByUser_Username(user.getUsername());
        if (cart == null)
            cart = cartRepository.save(Cart.builder()
                    .user(user)
                    .build());
        this.watch.stop();
        return cart;
    }

    @Override
    public void saveCart(Cart cart) {
        watch = new StopWatch();
        this.watch.start();
        cartRepository.save(cart);
        this.watch.stop();
    }

    @Override
    public void addItemToCart(Cart cart, CartItem cartItem) {
        watch = new StopWatch();
        Set<CartItem> cartItems = cart.getCartItems();
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                if (Objects.equals(item.getProductItem().getId(), cartItem.getProductItem().getId())) {
                    int quantity = item.getQuantity();
                    item.setQuantity(++quantity);
                    watch.start();
                    cartItemRepository.save(item);
                    cart.setTotal(getTotal(cart));
                    cartRepository.save(cart);
                    watch.stop();
                    return;
                }
            }
        }
        watch.start();
        cartItemRepository.save(cartItem);

        cart.setTotal(getTotal(cart));
        cartRepository.save(cart);
        watch.stop();
    }

    @Override
    public void emptyCart(Cart cart) {

        watch = new StopWatch();

        cart.setCartItems(null);
        cart.setTotal(0);
        watch.start();
        cartRepository.save(cart);
        deleteCartItems(cart);
        watch.stop();
    }

    private void deleteCartItems(Cart cart) {
        Set<CartItem> cartItems = cartItemRepository.getByCart_Id(cart.getId());
        if (cartItems != null) {
            cartItemRepository.deleteAll(cartItems);
        }

    }

    private double getTotal(Cart cart) {
        Set<CartItem> cartItems = cartItemRepository.getByCart_Id(cart.getId());
        double total = 0;
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                total += item.getQuantity() * item.getProductItem().getPrice();
            }
        }
        return total;
    }
}
