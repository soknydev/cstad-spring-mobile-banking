package co.istad.banking.features.account.dto;

import co.istad.banking.features.acoutypes.AccountTypeResponse;
import co.istad.banking.features.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        String alias,
        //UserResponse user,
        String actNo,
        String actName,
        BigDecimal balance,
        AccountTypeResponse accountTypeResponse,
        UserResponse userResponse

) {
}
