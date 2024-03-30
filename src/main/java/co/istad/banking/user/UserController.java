package co.istad.banking.user;

import co.istad.banking.user.dto.UserCreateRequest;
import co.istad.banking.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNewUser(userCreateRequest);
    }

    @ResponseStatus(HttpStatus.GONE)
    @GetMapping
    List<UserResponse> findAllUsers(){
        return userService.findUsers();
    }

}

