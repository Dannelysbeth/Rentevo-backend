package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.mapper.definition;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.request.UserRequest;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.DTO.response.UserResponse;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    User tranformRequestToUser(UserRequest userRequest);

    UserResponse transformUserToResponse(User user);
}
