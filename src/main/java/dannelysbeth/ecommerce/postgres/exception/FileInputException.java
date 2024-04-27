package dannelysbeth.ecommerce.postgres.exception;

import org.springframework.http.HttpStatus;

public class FileInputException extends BusinessException {
    public FileInputException(String msg) {
        super(msg, HttpStatus.NOT_ACCEPTABLE.value());
    }
}
