package dannelysbeth.rentevo.rentevo_backend.postgres.mapper.definition;

import dannelysbeth.rentevo.rentevo_backend.postgres.enums.Role;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.request.UserRequest;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.DTO.response.UserResponse;
import dannelysbeth.rentevo.rentevo_backend.postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    User tranformRequestToUser(UserRequest request, String encodedPassword, Role role);

    UserResponse transformUserToResponse(User user);
}
