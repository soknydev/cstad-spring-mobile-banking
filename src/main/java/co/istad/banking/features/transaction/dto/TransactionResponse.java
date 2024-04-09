package co.istad.banking.features.transaction.dto;

import co.istad.banking.features.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponse(
        AccountSnippetResponse owner,
        AccountSnippetResponse transferReceiver,
        BigDecimal amount,
        String remark,
        String transactionType,
        Boolean status,
        LocalDateTime transactionAt
) {
}
