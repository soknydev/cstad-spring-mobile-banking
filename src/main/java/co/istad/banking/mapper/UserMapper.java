package co.istad.banking.mapper;

import co.istad.banking.domain.User;
import co.istad.banking.user.dto.UserCreateRequest;
import co.istad.banking.user.dto.UserDetailsResponse;
import co.istad.banking.user.dto.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /* formaular
    * SourceType = UserCreateRequest (Parameter)
    * TargetType = User (UserReturn)
    * */

    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    void fromUserCreateRequest2(@MappingTarget User user, UserCreateRequest userCreateRequest);

    UserDetailsResponse toUserDetailsResponse(User user);

    User fromUserDetailsResponse(UserDetailsResponse userDetailsResponse);

    User fromUserUpdateRequest(UserUpdateRequest userUpdateRequest);
    void fromUserUpdateRequest2(@MappingTarget User user, String uuId, UserUpdateRequest userUpdateRequest);

}
