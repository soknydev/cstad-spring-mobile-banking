package co.istad.banking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(

        @NotBlank(message = "refresh token is requred")
        String refreshToken
) {
}
