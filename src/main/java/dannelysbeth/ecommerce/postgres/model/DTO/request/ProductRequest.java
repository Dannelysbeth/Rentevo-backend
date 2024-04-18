package dannelysbeth.ecommerce.postgres.model.DTO.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ProductRequest {

    String productCode;

    String name;

    String description;

    double price;

    String category;

    List<Feature> features;

    long quantityInStock;

    String SKU;

}




