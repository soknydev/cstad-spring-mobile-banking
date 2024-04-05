package co.istad.banking.features.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(
        @NotNull(message = "Pin is required")
        @Max(value = 9999, message = "Pin must be 4 digits")
        @Positive(message = "Pin must be a positive number")
        Integer pin,

        @NotBlank(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must be less than 20 characters")
        String phoneNumber,

        @NotBlank(message = "Password is required")
        String password,
        @NotBlank
        String confirmedPassword,

        @Column(length = 50)
        String name,
        @NotBlank
        @Size(max = 6)
        String gender,
        @NotNull
        LocalDate dob,
        @NotBlank
        @Size(max = 20)
        String nationalCardId,
        @Size(max = 20)
        String studentIdCard,

        @NotNull
        @NotEmpty
        List<RoleRequest> roles
) {
}
