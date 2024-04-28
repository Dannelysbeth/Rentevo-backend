package dannelysbeth.ecommerce.postgres.auth;

import dannelysbeth.ecommerce.postgres.auth.DTO.request.AuthenticationRequest;
import dannelysbeth.ecommerce.postgres.auth.DTO.request.RegisterRequest;
import dannelysbeth.ecommerce.postgres.auth.DTO.response.AuthenticationResponse;
import dannelysbeth.ecommerce.postgres.exception.EmailExistsException;
import dannelysbeth.ecommerce.postgres.exception.IncorrectPasswordException;
import dannelysbeth.ecommerce.postgres.exception.UserNotFoundException;
import dannelysbeth.ecommerce.postgres.exception.UsernameAlreadyTakenException;
import dannelysbeth.ecommerce.postgres.mapper.definition.UserMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.UserRequest;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.model.enums.Role;
import dannelysbeth.ecommerce.postgres.repository.UserRepository;
import dannelysbeth.ecommerce.postgres.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    public AuthenticationResponse register(RegisterRequest request) {
        verifyRequestCorrectness(request);
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstname(request.getEmail())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN_ROLE)
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepository.getUserByUsername(request.getUsername()).orElseGet(() -> userRepository.getUserByEmail(request.getUsername())
                .orElseThrow(UserNotFoundException::new));
        boolean decoded = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!decoded) {
            throw new IncorrectPasswordException();
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void importMultipleUsers(Set<UserRequest> requests) {
        requests.forEach(req -> {
            userRepository.save(userMapper.tranformRequestToUser(req,
                    passwordEncoder.encode(req.getUsername()),
                    Role.USER_ROLE));
        });
    }


    private void verifyRequestCorrectness(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException();
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }
    }
}
