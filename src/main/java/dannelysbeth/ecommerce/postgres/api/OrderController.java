package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.exception.NotEnoughProductException;
import dannelysbeth.ecommerce.postgres.mapper.definition.OrderMapper;
import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.model.DTO.request.OrderRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.GlobalResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.OrderResponse;
import dannelysbeth.ecommerce.postgres.service.definition.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

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
    private final ProductService productService;

    private final OrderMapper orderMapper;


    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @PostMapping("/create")
    public ResponseEntity<GlobalResponse> createOrder(@RequestBody OrderRequest request) {
        User loggedUser = userService.getLoggedUser();
        Cart cart = cartService.getCartByUser(loggedUser);
        double time = cartService.getRepositoryResponseTime();
        ShippingMethod shippingMethod = shippingMethodService.getShippingMethodByCode(request.getShippingMethod());
        Order order = orderService.createOrder(loggedUser);
        time += orderService.getRepositoryResponseTime();

        Address shippingAddress = addressService.getAddressById(request.getAddressId());
        order = orderMapper.updateOrderFromRequest(order, request, cart, shippingAddress, shippingMethod);

        try {
            productService.decreaseProductItems(order);
            time += productService.getRepositoryResponseTime();
            orderService.updateOrder(order);
            time += orderService.getRepositoryResponseTime();
            cartService.emptyCart(cart);
            time += cartService.getRepositoryResponseTime();
            return ResponseEntity.ok()
                    .body(GlobalResponse.builder()
                            .responseTime(time+"ms")
                            .entries(Collections.singleton("Order was successfully created"))
                                    .build()
                            );

        } catch(NotEnoughProductException ex) {
            orderService.deleteOrder(order);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(GlobalResponse.builder()
                            .responseTime(time+"ms")
                            .entries(Collections.singleton("Order was successfully created"))
                            .build()
                    );
        }

    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    @GetMapping("")
    public ResponseEntity<Set<OrderResponse>> getLoggedUserOrders() {
        User loggedUser = userService.getLoggedUser();

        Set<Order> orders = orderService.getOrdersByUser(loggedUser.getUsername());

        Set<OrderResponse> orderResponse = orderMapper.transformToOrderResponse(orders);

        return ResponseEntity.ok()
                .body(orderResponse);
    }

}
