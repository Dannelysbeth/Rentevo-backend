package dannelysbeth.ecommerce.postgres.postgres.model.DTO.request;

import dannelysbeth.ecommerce.postgres.postgres.model.Country;
import lombok.Data;

@Data
public class AddressRequest {
    Country country;
    int unitNumber;
    int streetNumber;
    String addressLine1;
    String addressLine2;
    String city;
    String postalCode;

}
