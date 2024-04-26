package dannelysbeth.ecommerce.postgres.model.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductResponse {

    String productCode;

    String name;

    String category;

    String description;

    double price;

    Set<ProductItemResponse> productItems;
}
