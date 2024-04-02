package dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.request;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;
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
