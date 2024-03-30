package co.istad.banking.user;

import co.istad.banking.domain.User;
import co.istad.banking.user.dto.UserCreateRequest;
import co.istad.banking.user.dto.UserDetailsResponse;
import co.istad.banking.user.dto.UserResponse;

import java.util.List;


public interface UserService {

    void createNewUser(UserCreateRequest createRequest);

    List<UserResponse> findUsers();
}
