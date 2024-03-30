package co.istad.banking.user.dto;

public record UserResponse(
        String name,
        String gender,
        Integer pin
) {
}
