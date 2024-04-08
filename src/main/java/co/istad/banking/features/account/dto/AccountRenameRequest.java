package co.istad.banking.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message = "New name is required")
        @Size(message = "Account name must be les than or equal 100")
        String newName

) {
}
