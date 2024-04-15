package dannelysbeth.ecommerce.postgres.auth.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String productCode;

    private String name;

    private String description;

    private double price;

    private String category;
}
