package dannelysbeth.ecommerce.postgres.model.DTO.request;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class ProductRequest {

    String productCode;

    String name;

    String description;

    String category;

    double price;

    Set<ProductItemRequest> productItems;

}




