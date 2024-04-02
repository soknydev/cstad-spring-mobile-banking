package co.istad.banking.features.user.dto;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String name,
        String gender,
        String profileImage,
        LocalDate dob

) {
}
