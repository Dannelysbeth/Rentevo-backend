package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api.handlers;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api.handlers.DTO.ErrorResponse;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.BusinessException;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.EmailExistsException;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.UserNotFoundException;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.UsernameAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse handleAnyException(AccessDeniedException exception, WebRequest request) {
        return new ErrorResponse(new BusinessException(exception.getMessage(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUsernameAlreadyTakenException(UsernameAlreadyTakenException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEmailExistsException(EmailExistsException exception) {
        return new ErrorResponse(exception);
    }
}
