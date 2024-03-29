package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.implementation;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.auth.AuthService;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.exception.UserNotFoundException;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.request.UserRequest;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository.UserRepository;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashSet;
import java.util.Set;

import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.UserSpecification.startsWithFirstname;
import static dannelysbeth.rentevo.postgres.rentevo_backend_postgres.filters.UserSpecification.startsWithLastname;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    StopWatch watch;

    @Override
    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.getByUsername(username);
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Set<User> findAllUsers(String firstname, String lastname) {
        Specification<User> filters = Specification.where(lastname == null ? null : startsWithLastname(lastname))
                .and(firstname == null ? null : startsWithFirstname(firstname));
        return new HashSet<>(userRepository.findAll(filters));
    }

    @Override
    public void importUsers(Set<UserRequest> requests) {
        this.authService.importMultipleUsers(requests);
    }

}