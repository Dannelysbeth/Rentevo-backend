package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception;

public class BusinessException extends RuntimeException {
    String message;
    int status;

    public BusinessException(String msg, int status) {
        this.message = msg;
        this.status = status;
    }
}
