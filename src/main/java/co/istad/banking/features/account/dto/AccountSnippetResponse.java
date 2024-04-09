package co.istad.banking.features.account.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountSnippetResponse(
        String accountNo,
        String accountName
) {
}
