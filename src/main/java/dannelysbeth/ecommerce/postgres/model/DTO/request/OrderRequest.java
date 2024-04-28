package dannelysbeth.ecommerce.postgres.model.DTO.request;

import dannelysbeth.ecommerce.postgres.model.ShippingMethod;
import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;
import lombok.Data;

@Data
public class OrderRequest {

    Long addressId;

    String shippingMethod;

}
