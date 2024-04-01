package dannelysbeth.rentevo.postgres.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    String message;
    int status;

    public BusinessException(String msg, int status) {
        super(msg);
        this.message = msg;
        this.status = status;
    }
}
