package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.mapper.definition.CartMapper;
import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.DTO.response.CartResponse;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.service.definition.CartService;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import dannelysbeth.ecommerce.postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    private final CartMapper cartMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @PostMapping("/addItem/{id}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long id) {
        ProductItem productItem = productService.getProductItemById(id);
        User loggedUser = userService.getLoggedUser();
        Cart myCart = cartService.getCartByUser(loggedUser);

        CartItem cartItem = cartMapper.getCartItemFromProductItem(productItem, myCart);


        this.cartService.addItemToCart(myCart, cartItem);
        return ResponseEntity.ok()
                .body("Item was added to cart");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @GetMapping()
    public ResponseEntity<CartResponse> getMyCart() {
        User loggedUser = userService.getLoggedUser();
        Cart myCart = cartService.getCartByUser(loggedUser);

        CartResponse cartResponse = cartMapper.transformToCartResponse(myCart);
        return ResponseEntity.ok()
                .body(cartResponse);
    }
}
