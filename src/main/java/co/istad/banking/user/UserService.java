package co.istad.banking.user;

import co.istad.banking.user.dto.UserCreateRequest;


public interface UserService {
    void createNewUser(UserCreateRequest createRequest);
}
