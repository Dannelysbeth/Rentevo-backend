package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends BusinessException {
    public EmailExistsException() {
        super("Email is already taken", HttpStatus.NOT_ACCEPTABLE.value());
    }
}
