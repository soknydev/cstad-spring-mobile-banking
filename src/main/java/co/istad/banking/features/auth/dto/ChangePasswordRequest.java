package co.istad.banking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank
        String oldPassword,

        @NotBlank
        String password,

        @NotBlank
        String confirmedPassword
) {
}
