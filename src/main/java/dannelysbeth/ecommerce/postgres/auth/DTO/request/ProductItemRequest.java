package dannelysbeth.ecommerce.postgres.auth.DTO.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductItemRequest {

    private ProductRequest product;

    private long quantityInStock;

    private long SKU;

    private List<VariationRequest> variation;
}


