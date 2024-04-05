package co.istad.banking.features.account;

import co.istad.banking.domain.Account;
import co.istad.banking.domain.AccountType;
import co.istad.banking.domain.User;
import co.istad.banking.domain.UserAccount;
import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.acoutypes.AccountTypeRepository;
import co.istad.banking.features.acoutypes.AccountTypeResponse;
import co.istad.banking.features.user.UserRepository;
import co.istad.banking.features.user.dto.UserCreateRequest;
import co.istad.banking.features.user.dto.UserResponse;
import co.istad.banking.mapper.AccountMapper;
import co.istad.banking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void creatNewAccount(AccountCreateRequest accountCreateRequest) {
        // check account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));

        // check user by UUID
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));

        // map account dto to account entity
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setAccountName(user.getName());
        account.setAccountNo("19");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);

    }

    @Override
    public AccountResponse findByActNo(String actNo) {
       Account account = accountRepository.findByAccountNo(actNo)
               .orElseThrow(()->
                       new ResponseStatusException(
                               HttpStatus.NOT_FOUND,
                               "actNo has been not found"
                       ));
       return accountMapper.toAccountResponse(account);
    }

    /*@Override
    public AccountResponse findByActNo(String actNo) {
        Account account = accountRepository.existsByAccountNo(actNo).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with ActNo not found. Please try again."));

        User user = account.getUserAccountList().stream().findFirst()
                .map(UserAccount::getUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User associated with Account not found."));
        log.info(account.getUserAccountList().toString());
        AccountTypeResponse accountTypeResponse = accountMapper.toAccountResponse(account.);

        AccountResponse accountResponse = accountMapper.toAccountResponse(account);

        UserResponse userResponse = userMapper.toUserRes(user);
        accountResponse = new AccountResponse(
                accountResponse.actNo(),
                accountResponse.actName(),
                accountResponse.alias(),
                accountResponse.balance(),
                accountTypeResponse,
                userResponse
        );

        return accountResponse;
    }
    toAccountTypeResponse(account.getAccountType()*/
}
