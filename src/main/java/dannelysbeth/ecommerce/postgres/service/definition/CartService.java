package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.User;

public interface CartService {

    Cart getCartByUser(User user);

    void saveCart(Cart cart);

    void addItemToCart(Cart cart, CartItem cartItem);

    void emptyCart(Cart cart);
}
