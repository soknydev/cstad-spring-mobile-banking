package co.istad.banking.features.user;

import co.istad.banking.base.BasedMassage;
import co.istad.banking.features.user.dto.*;
import co.istad.banking.features.user.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    void createNewUser(UserCreateRequest createRequest);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse updatePasswordByUuid(String uuid, UserChangePwdRequest userChangePwdRequest);

    BasedMassage blockByUuid(String uuid);

    void deleteUserByUuid(String uuid);

    void disableUserByUuid(String uuid);

    void enableUserByUuid(String uuid);

    List<UserDetailsResponse> findUsers();

    UserDetailsResponse findByUuid(String uuid);

    Page<UserResponse> findList(int page, int limit);

    String updateProfileImage(String mediaName, String uuid);

}
