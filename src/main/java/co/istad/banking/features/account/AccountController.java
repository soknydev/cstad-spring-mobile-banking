package co.istad.banking.features.account;

import co.istad.banking.features.account.dto.AccountCreateRequest;
import co.istad.banking.features.account.dto.AccountRenameRequest;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.account.dto.AccountUpdateTransferLimit;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.creatNewAccount(accountCreateRequest);
    }

    @GetMapping("{accountNo}")
    AccountResponse findByActNo(@PathVariable String accountNo) {
        return accountService.findByActNo(accountNo);
    }

    @PutMapping("/{accountNo}/rename")
    AccountResponse renameByActNo(@PathVariable String accountNo,
                                  @Valid @RequestBody AccountRenameRequest newName){
        return accountService.renameByActNo(accountNo, newName);
    }

    @PutMapping("/{accountNo}/hide")
    void hideAccount(@PathVariable String accountNo){
        accountService.hideAccount(accountNo);
    }

    @GetMapping
    Page<AccountResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return accountService.findList(page, size);
    }

    @PutMapping("{accountNo}/transferLimit")
    void updateTransferLimitByActNo(@PathVariable String accountNo,
                                        @RequestBody AccountUpdateTransferLimit updateTransferLimit){
        accountService.updateTransferLimitByActNo(accountNo, updateTransferLimit);
    }

}
