package co.istad.banking.features.account.dto;

import co.istad.banking.features.acoutypes.AccountTypeResponse;
import co.istad.banking.features.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        String alias,
        //UserResponse user,
        String accountNo,
        String accountName,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountTypeResponse,
        UserResponse userResponse

) {
}
