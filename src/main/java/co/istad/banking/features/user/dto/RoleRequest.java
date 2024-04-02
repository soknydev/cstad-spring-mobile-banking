package co.istad.banking.features.user.dto;

import jakarta.validation.constraints.NotNull;

public record RoleRequest(
        @NotNull
        String name
) {
}
