package dannelysbeth.ecommerce.postgres.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends BusinessException {
    public IncorrectPasswordException() {
        super("Wrong password!", HttpStatus.UNAUTHORIZED.value());
    }
}
