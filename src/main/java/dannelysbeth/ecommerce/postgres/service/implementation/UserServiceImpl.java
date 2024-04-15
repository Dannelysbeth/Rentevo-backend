package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.auth.AuthService;
import dannelysbeth.ecommerce.postgres.exception.UserNotFoundException;
import dannelysbeth.ecommerce.postgres.filters.UserSpecification;
import dannelysbeth.ecommerce.postgres.model.DTO.request.UserRequest;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.repository.UserRepository;
import dannelysbeth.ecommerce.postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashSet;
import java.util.Set;

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
        Specification<User> filters = Specification.where(lastname == null ? null : UserSpecification.startsWithLastname(lastname))
                .and(firstname == null ? null : UserSpecification.startsWithFirstname(firstname));
        return new HashSet<>(userRepository.findAll(filters));
    }

    @Override
    public void importUsers(Set<UserRequest> requests) {
        this.authService.importMultipleUsers(requests);
    }

}