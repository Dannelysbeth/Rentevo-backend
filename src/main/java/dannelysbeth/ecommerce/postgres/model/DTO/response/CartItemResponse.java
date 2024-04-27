package dannelysbeth.ecommerce.postgres.model.DTO.response;

import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CartItemResponse {

    String productCode;

    String name;

    String category;

    String description;

    double price;

    Set<Feature> featureSet;

    long quantity;
}
