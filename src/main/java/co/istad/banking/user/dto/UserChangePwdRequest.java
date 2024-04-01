package co.istad.banking.user.dto;

public record UserChangePwdRequest(
        String oldPwd,
        String newPwd,
        String confirmedPwd
) {
}
