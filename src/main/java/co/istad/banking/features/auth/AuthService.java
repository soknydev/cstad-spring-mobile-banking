package co.istad.banking.features.auth;

import co.istad.banking.features.auth.dto.AuthResponse;
import co.istad.banking.features.auth.dto.ChangePasswordRequest;
import co.istad.banking.features.auth.dto.LoginRequest;
import co.istad.banking.features.auth.dto.RefreshTokenRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(RefreshTokenRequest request);

    void changePassword(ChangePasswordRequest  changePasswordRequest, Jwt jwt);

}
