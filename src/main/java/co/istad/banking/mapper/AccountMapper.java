package co.istad.banking.mapper;

import co.istad.banking.domain.Account;
import co.istad.banking.domain.AccountType;
import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    AccountResponse toAccountResponse(Account account);

    //Account fromUserCreateRequest(AccountCreateRequest accountCreateRequest);
}
