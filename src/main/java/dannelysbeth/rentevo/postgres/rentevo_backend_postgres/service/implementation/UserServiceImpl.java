package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.implementation;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository.UserRepository;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }
}
