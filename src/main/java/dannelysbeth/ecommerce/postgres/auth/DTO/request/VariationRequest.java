package dannelysbeth.ecommerce.postgres.auth.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationRequest {
    private String parameter;
    private VariationOptionRequest type;
}
