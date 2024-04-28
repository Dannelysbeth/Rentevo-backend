package dannelysbeth.ecommerce.postgres.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends BusinessException {
    public AddressNotFoundException() {
        super("Address not found", HttpStatus.NOT_FOUND.value());
    }
}
