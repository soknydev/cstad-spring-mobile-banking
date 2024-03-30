package co.istad.banking.user.dto;

public record UserDetailsResponse(
        Integer pin,
        String phoneNumber,
        String password,
        String confirmedPassword,
        String  name,
        String gender,
        String dob,
        String nationalCard,
        String studentIdCard
) {

}
