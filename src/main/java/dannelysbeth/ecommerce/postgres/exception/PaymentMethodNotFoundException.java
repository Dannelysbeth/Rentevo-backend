package dannelysbeth.ecommerce.postgres.exception;

import org.springframework.http.HttpStatus;

public class PaymentMethodNotFoundException extends BusinessException {
    public PaymentMethodNotFoundException() {
        super("Payment method not found", HttpStatus.NOT_FOUND.value());
    }
}
