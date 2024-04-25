package co.istad.banking.features.auth;

import co.istad.banking.features.auth.dto.AuthResponse;
import co.istad.banking.features.auth.dto.ChangePasswordRequest;
import co.istad.banking.features.auth.dto.LoginRequest;
import co.istad.banking.features.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest request){
        return authService.refresh(request);
    }

    @PostMapping("change-password")
    void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                        @AuthenticationPrincipal Jwt jwt){
        authService.changePassword(changePasswordRequest, jwt);
    }
}

