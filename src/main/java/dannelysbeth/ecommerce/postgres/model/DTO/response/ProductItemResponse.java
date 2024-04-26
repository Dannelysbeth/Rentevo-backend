package dannelysbeth.ecommerce.postgres.model.DTO.response;

import dannelysbeth.ecommerce.postgres.model.DTO.Feature;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductItemResponse {

    double price;

    Set<Feature> featureSet;

    long quantityInStock;

    String SKU;

}
