package co.istad.banking.features.acoutypes;

import co.istad.banking.domain.AccountType;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);
}
