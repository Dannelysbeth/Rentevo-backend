package dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.response;

import dannelysbeth.rentevo.rentevo_backend.postgres.model.Country;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AddressResponse {
    Country country;
    Person user;
    int unitNumber;
    int streetNumber;
    String addressLine1;
    String addressLine2;
    String city;
    String postalCode;
    boolean isDefault;

    @Builder
    public static class Person {
        String firstname;
        String lastname;
    }
}
