package co.istad.banking.user;

import co.istad.banking.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNewUser(userCreateRequest);
    }

}

