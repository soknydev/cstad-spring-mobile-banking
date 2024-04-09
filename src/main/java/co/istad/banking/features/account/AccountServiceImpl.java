package co.istad.banking.features.account;

import co.istad.banking.domain.Account;
import co.istad.banking.domain.AccountType;
import co.istad.banking.domain.User;
import co.istad.banking.domain.UserAccount;
import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountRenameRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.account.dto.AccountUpdateTransferLimit;
import co.istad.banking.features.acoutypes.AccountTypeRepository;
import co.istad.banking.features.acoutypes.AccountTypeResponse;
import co.istad.banking.features.user.UserRepository;
import co.istad.banking.features.user.dto.UserCreateRequest;
import co.istad.banking.features.user.dto.UserResponse;
import co.istad.banking.mapper.AccountMapper;
import co.istad.banking.mapper.AccountTypeMapper;
import co.istad.banking.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private final AccountTypeMapper accountTypeMapper;


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
        account.setAccountNo("1231165416");
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


    /*@Override
    public AccountResponse findByActNo(String actNo) {
       Account account = accountRepository.findByAccountNo(actNo)
               .orElseThrow(()->
                       new ResponseStatusException(
                               HttpStatus.NOT_FOUND,
                               "actNo has been not found"
                       ));
       return accountMapper.toAccountResponse(account);
    }*/

    @Override
    public AccountResponse findByActNo(String accountNo) {
        /*Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with ActNo not found. Please try again."));*/

        /*User user = account.getUserAccountList().stream().findFirst()
                .map(UserAccount::getUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User associated with Account not found."));
        log.info(account.getUserAccountList().toString());
        AccountTypeResponse accountTypeResponse = accountTypeMapper.toAccountTypeResponse(account.getAccountType());
        AccountResponse accountResponse = accountMapper.toAccountResponse(account);
        UserResponse userResponse = userMapper.toUserResponse(user);
        accountResponse = new AccountResponse(
                accountResponse.accountNo(),
                accountResponse.accountName(),
                accountResponse.alias(),
                accountResponse.balance(),
                accountTypeResponse,
                userResponse
        );
        return accountResponse;*/

        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account with ActNo not found. Please try again."));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse renameByActNo(String accountNo, AccountRenameRequest accountRenameRequest) {
        // check actNo exist
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account has been not found...!"
                        ));
        // check old alias
        if(account.getAlias() != null &&  account.getAlias().equals(accountRenameRequest.newName()) ){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "new name the same old name, can not rename, please try again...!"
            );
        }
        account.setAlias(accountRenameRequest.newName());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void hideAccount(String accountNo) {
        if(!accountRepository.existsByAccountNo(accountNo)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has been not found...!"
            );
        }

        try {
            accountRepository.hideAccountByActNo(accountNo);
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "something went wrong ...!, please call to me"
            );
        }
    }

    @Override
    public Page<AccountResponse> findList(int page, int size) {
        // validate page and size
        if(page<0){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Page number must be grater than 0");
        }
        if(size<1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "size number must be grater than or equal 1");
        }
        Sort sortByAccountName = Sort.by(Sort.Direction.ASC, "accountName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByAccountName);
        Page<Account> accounts = accountRepository.findAll(pageRequest);
        return accounts.map(accountMapper::toAccountResponse);
    }

    @Override
    public AccountResponse updateTransferLimit(String accountNO, AccountUpdateTransferLimit updateTransferLimit) {
        if(!accountRepository.existsByAccountNo(accountNO)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has been not found...!"
            );
        }

        // if old limit  = new limit
        Account account = new Account();
        /*if(account.getTransferLimit().equals(updateTransferLimit.newTransferLimit())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "new transfer limit the same old limit transfer...!"
            );
        }*/
        account.setTransferLimit(updateTransferLimit.newTransferLimit());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Transactional
    @Override
    public void updateTransferLimitByActNo(String accountNo, AccountUpdateTransferLimit updateTransferLimit) {
        if(!accountRepository.existsByAccountNo(accountNo)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has been not found...!"
            );
        }
        accountRepository.updateAccountByAccountNo(accountNo, updateTransferLimit.newTransferLimit());
    }
}
