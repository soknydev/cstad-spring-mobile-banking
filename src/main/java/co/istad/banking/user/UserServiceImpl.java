package co.istad.banking.user;

import co.istad.banking.domain.User;
import co.istad.banking.mapper.UserMapper;
import co.istad.banking.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createNewUser(UserCreateRequest createRequest) {
        User user = userMapper.fromUserCreateRequest(createRequest);
        userRepository.save(user);

    }
}
