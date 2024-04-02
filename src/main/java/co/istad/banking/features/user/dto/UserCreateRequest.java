package co.istad.banking.features.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(
        @NotNull
        @Max(9999)
        @Positive
        Integer pin,

        @NotBlank
        @Size(max = 20)
        String phoneNumber,

        @NotBlank
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
