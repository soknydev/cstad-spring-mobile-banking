package co.istad.banking.mapper;

import co.istad.banking.domain.User;
import co.istad.banking.features.user.dto.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
    List<UserDetailsResponse> toUserDetailsResponseList(List<User> users);

    User fromUserDetailsResponse(UserDetailsResponse userDetailsResponse);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    User fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUserUpdatePwdRequest(UserChangePwdRequest userChangePwdRequest, @MappingTarget User user);

    void fromUserUpdateRequest2(@MappingTarget User user, String uuId, UserUpdateRequest userUpdateRequest);

    UserResponse toUserResponse(User user);
}
