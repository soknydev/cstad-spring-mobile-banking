package co.istad.banking.features.account.dto;

import java.math.BigDecimal;

public record AccountUpdateTransferLimit(
        BigDecimal newTransferLimit
) {
}
