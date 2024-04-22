package dannelysbeth.ecommerce.postgres.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Feature {

    String parameter;

    String value;
}
