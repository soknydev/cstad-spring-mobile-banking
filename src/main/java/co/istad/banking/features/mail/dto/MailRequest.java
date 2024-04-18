package co.istad.banking.features.mail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MailRequest(

        @NotBlank(message = "Email is required")
        String to,

        @NotBlank(message = "Subject is required")
        String subject,

        String body
) {
}
