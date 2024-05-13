package dannelysbeth.ecommerce.postgres.model.DTO.request;

import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductItemRequest {

    double price;

    Set<Feature> features;

    long quantityInStock;

    String SKU;
}
