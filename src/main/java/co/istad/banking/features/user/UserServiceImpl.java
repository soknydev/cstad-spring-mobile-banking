package co.istad.banking.features.user;


import co.istad.banking.base.BasedMassage;
import co.istad.banking.domain.User;
import co.istad.banking.domain.Role;
import co.istad.banking.features.user.dto.*;
import co.istad.banking.mapper.UserMapper;
import co.istad.banking.features.user.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public void createNewUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());

        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));
        //Create dynamic role from client
        userCreateRequest.roles().forEach(r-> {
            Role newRole = roleRepository.findByName(r.name())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Role USER has not been found!"));
            roles.add(newRole);
        });
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }


    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {
        // check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        log.info("before user: {}", user);
        userMapper.fromUserUpdateRequest(userUpdateRequest, user);
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updatePasswordByUuid(String uuid, UserChangePwdRequest userChangePwdRequest) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"
                        ));

        // Check if the new password and confirmed password match
        if (!userChangePwdRequest.newPwd().equals(userChangePwdRequest.confirmedPwd())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "New password and confirm password do not match!"
            );
        }

        user.setPassword(userChangePwdRequest.newPwd());

       user = userRepository.save(user);
       return userMapper.toUserResponse(user);
    }

    @Transactional
    @Override
    public BasedMassage blockByUuid(String uuid) {

        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMassage("User has been blocked");
    }

    @Override
    public void deleteUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"
                        ));
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void disableUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found!"
                ));
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void enableUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found!"
                ));
        user.setIsDeleted(false);
        userRepository.save(user);
    }

    @Override
    public List<UserDetailsResponse> findUsers() {
        List<User> user = userRepository.findAll();
        return userMapper.toUserDetailsResponseList(user);
    }

    @Override
    public UserDetailsResponse findByUuid(String uuid) {
        //check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "USER has not been found!"));
        user.getRoles().forEach(role -> log.info("Roles: {}", role));
        return userMapper.toUserDetailsResponse(user);
    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(userMapper::toUserResponse);
    }

    @Override
    public String updateProfileImage(String mediaName, String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "USER has not been found!"));
        user.setProfileImage(mediaName);
        userRepository.save(user);
        return baseUri + "Images/" + mediaName;
    }


}
