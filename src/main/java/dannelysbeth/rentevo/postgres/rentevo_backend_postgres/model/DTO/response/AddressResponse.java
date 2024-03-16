package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class AddressResponse {
    @Builder
    public static class Person {
        String firstname;
        String lastname;
    }
    Country country;
    Person user;
    int unitNumber;
    int streetNumber;
    String addressLine1;
    String addressLine2;
    String city;
    String postalCode;
    boolean isDefault;
}
