package co.istad.banking.features.auth;

import co.istad.banking.features.auth.dto.AuthResponse;
import co.istad.banking.features.auth.dto.LoginRequest;
import co.istad.banking.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Auth with DTO
        Authentication auth  = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );
        auth = daoAuthenticationProvider.authenticate(auth);
        log.info("Auth :"+auth.getPrincipal());

        Instant now = Instant.now();
        CustomUserDetails customUserDetail = (CustomUserDetails) auth.getPrincipal();
        log.info(customUserDetail.getUsername());
        log.info(customUserDetail.getUser().getName());
        customUserDetail.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(customUserDetail.getUsername())
                .subject("Access Resource")
                .audience(List.of("WEB","MOBILE"))
                .issuedAt(now)
                .expiresAt(now.plus(20, ChronoUnit.SECONDS))
                .issuer(customUserDetail.getUsername())
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return new AuthResponse(
                "Bearer",
                accessToken,
                "OK BRO"
        );

    }
}
