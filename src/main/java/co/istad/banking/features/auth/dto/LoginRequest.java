package co.istad.banking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "phone number is required")
        String phoneNumber,

        @NotBlank(message = "password is required")
        String password
) {
}
