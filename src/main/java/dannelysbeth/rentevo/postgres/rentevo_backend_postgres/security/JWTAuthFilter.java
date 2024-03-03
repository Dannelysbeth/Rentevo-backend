package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api.handlers.DTO.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter {
    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;

    private void addErrorMessageToResponse(HttpServletResponse response, int errorCode, String errorMessage) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(errorCode, errorMessage));
    }
}
