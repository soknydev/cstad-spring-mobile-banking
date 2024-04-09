package co.istad.banking.features.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionCreateRequest(

        @NotBlank(message = "Account not is required")
        String ownerActNo,

        @NotBlank(message = "transaction receive is required")
        String transactionReceiveActNo,

        @NotNull(message = "Amount re required")
        BigDecimal amount,

        String remark

) {
}
