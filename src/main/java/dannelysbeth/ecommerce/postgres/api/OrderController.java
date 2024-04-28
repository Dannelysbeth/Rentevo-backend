package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.mapper.definition.OrderMapper;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.model.DTO.request.OrderRequest;
import dannelysbeth.ecommerce.postgres.service.definition.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final ShippingMethodService shippingMethodService;

    private final OrderMapper orderMapper;



    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        User loggedUser = userService.getLoggedUser();
        Cart cart = cartService.getCartByUser(loggedUser);
        ShippingMethod shippingMethod = shippingMethodService.getShippingMethodByCode(request.getShippingMethod());
        Order order = orderService.createOrder(loggedUser);

        Address shippingAddress = addressService.getAddressById(request.getAddressId());
        order = orderMapper.updateOrderFromRequest(order, request, cart, shippingAddress, shippingMethod);

        orderService.updateOrder(order);
        cartService.emptyCart(cart);


        return ResponseEntity.ok()
                .body("Order was successfully created");
    }

}
