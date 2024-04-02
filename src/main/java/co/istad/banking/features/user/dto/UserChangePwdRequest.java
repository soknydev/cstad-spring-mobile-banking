package co.istad.banking.features.user.dto;

public record UserChangePwdRequest(
        String oldPwd,
        String newPwd,
        String confirmedPwd
) {
}
