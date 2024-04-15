package dannelysbeth.ecommerce.postgres.auth.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationOptionRequest {

    String name;
}
