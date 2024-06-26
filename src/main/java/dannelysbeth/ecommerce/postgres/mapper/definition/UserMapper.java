package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.DTO.request.UserRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.UserResponse;
import dannelysbeth.ecommerce.postgres.model.User;
import dannelysbeth.ecommerce.postgres.model.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    User tranformRequestToUser(UserRequest request, String encodedPassword, Role role);

    UserResponse transformUserToResponse(User user);
}
