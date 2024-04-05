package co.istad.banking.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,

        @NotNull(message = "First balance is required 5$ up")
        BigDecimal balance,

        @NotBlank(message = "Account type is required")
        String accountTypeAlias,

        @NotBlank(message = "user owner is required")
        String userUuid,

        String cardNumber
) {
}
