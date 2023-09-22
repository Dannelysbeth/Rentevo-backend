package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException{
    UserNotFoundException(String username) {
        String msg = "User \{username} was not found";
        super(msg, HttpStatus.NOT_FOUND.value());
    }
}
