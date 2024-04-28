package dannelysbeth.ecommerce.postgres.model.DTO.request;

import dannelysbeth.ecommerce.postgres.model.Country;
import lombok.Data;

@Data
public class AddressRequest {

    String country;

    int unitNumber;

    int streetNumber;

    String addressLine1;

    String addressLine2;

    String city;

    String postalCode;

}
