package co.istad.banking.mapper;

import co.istad.banking.domain.Account;
import co.istad.banking.domain.AccountType;
import co.istad.banking.domain.User;
import co.istad.banking.domain.UserAccount;
import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.acoutypes.AccountTypeResponse;
import co.istad.banking.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "userResponse", qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.getFirst().getUser());
    }

    UserResponse toUserResponse(User user);

    //Account fromUserCreateRequest(AccountCreateRequest accountCreateRequest);
}
