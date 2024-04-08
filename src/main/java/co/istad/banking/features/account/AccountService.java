package co.istad.banking.features.account;

import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.user.dto.UserCreateRequest;

public interface AccountService {

    void creatNewAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String accountNo);
}
