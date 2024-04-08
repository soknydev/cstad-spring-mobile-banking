package co.istad.banking.features.account;

import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountRenameRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.user.dto.UserCreateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    void creatNewAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String accountNo);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);

    void hideAccount(String accountNo);

    Page<AccountResponse> findList(int page, int size);
}
