package co.istad.banking.user.dto;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String name,
        String gender,
        String profileImage,
        LocalDate dob

) {
}
