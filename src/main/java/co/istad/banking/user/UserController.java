package co.istad.banking.user;

import co.istad.banking.base.BasedMassage;
import co.istad.banking.user.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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


    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }

    @PutMapping("/{uuid}/block")
    BasedMassage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    @DeleteMapping("{uuid}")
    void deleteByUuid(@PathVariable String uuid){
        userService.deleteUserByUuid(uuid);
    }

    @PutMapping("{uuid}/disable")
    void disableByUuid(@PathVariable String uuid){
        userService.disableUserByUuid(uuid);
    }

    @PutMapping("{uuid}/enable")
    void enableByUuid(@PathVariable String uuid){
        userService.enableUserByUuid(uuid);
    }

    @PutMapping("{uuid}/changePassword")
    UserResponse changedPasswordByUuid(@PathVariable String uuid,
                                       @RequestBody UserChangePwdRequest userChangePwdRequest){
        return userService.updatePasswordByUuid(uuid, userChangePwdRequest);
    }

    @GetMapping
    Page<UserResponse> findList(
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "2") int limit){
        return userService.findList(page, limit);

    }


}

