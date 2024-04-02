package dannelysbeth.rentevo.rentevo_backend.auth;

import dannelysbeth.rentevo.rentevo_backend.auth.DTO.request.AuthenticationRequest;
import dannelysbeth.rentevo.rentevo_backend.auth.DTO.request.RegisterRequest;
import dannelysbeth.rentevo.rentevo_backend.auth.DTO.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
