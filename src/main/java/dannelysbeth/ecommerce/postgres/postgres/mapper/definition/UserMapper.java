package dannelysbeth.ecommerce.postgres.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.enums.Role;
import dannelysbeth.ecommerce.postgres.postgres.model.DTO.request.UserRequest;
import dannelysbeth.ecommerce.postgres.postgres.model.DTO.response.UserResponse;
import dannelysbeth.ecommerce.postgres.postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    User tranformRequestToUser(UserRequest request, String encodedPassword, Role role);

    UserResponse transformUserToResponse(User user);
}
