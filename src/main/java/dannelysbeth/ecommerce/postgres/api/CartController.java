package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.mapper.definition.CartMapper;
import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.DTO.response.CartResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.GlobalResponse;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.service.definition.CartService;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import dannelysbeth.ecommerce.postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

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
    public ResponseEntity<GlobalResponse> addItemToCart(@PathVariable Long id) {
        double miliSec = 0;
        ProductItem productItem = productService.getProductItemById(id);
        miliSec = productService.getRepositoryResponseTime();
        User loggedUser = userService.getLoggedUser();
        Cart myCart = cartService.getCartByUser(loggedUser);
        miliSec += cartService.getRepositoryResponseTime();
        CartItem cartItem = cartMapper.getCartItemFromProductItem(productItem, myCart);

        this.cartService.addItemToCart(myCart, cartItem);
        miliSec += cartService.getRepositoryResponseTime();
        return ResponseEntity.ok()
                .body(GlobalResponse.builder()
                        .entries(new HashSet<>(Collections.singleton("Item was added to cart")))
                        .responseTime(productService.getRepositoryResponseTime()+"ms")
                        .build());
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
