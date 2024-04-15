package dannelysbeth.ecommerce.postgres.mapper.implementation;

import dannelysbeth.ecommerce.postgres.enums.Role;
import dannelysbeth.ecommerce.postgres.mapper.definition.UserMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.UserRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.UserResponse;
import dannelysbeth.ecommerce.postgres.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User tranformRequestToUser(UserRequest request, String encodedPassword, Role role) {
        return User.builder()
                .role(role)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .build();
    }

    @Override
    public UserResponse transformUserToResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
