package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api.handlers;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api.handlers.DTO.ErrorResponse;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.BusinessException;
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
}
