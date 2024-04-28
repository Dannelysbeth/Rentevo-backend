package dannelysbeth.ecommerce.postgres.model.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CartResponse {

    String username;

    Set<CartItemResponse> items;

    double total;
}
