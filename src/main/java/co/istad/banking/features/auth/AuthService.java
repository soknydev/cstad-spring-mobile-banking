package co.istad.banking.features.auth;

import co.istad.banking.features.auth.dto.AuthResponse;
import co.istad.banking.features.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

}
