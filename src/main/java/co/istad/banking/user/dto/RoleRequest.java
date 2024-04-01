package co.istad.banking.user.dto;

import jakarta.validation.constraints.NotNull;

public record RoleRequest(
        @NotNull
        String name
) {
}
