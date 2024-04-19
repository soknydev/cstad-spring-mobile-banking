package co.istad.banking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // TODO: your security logic
        httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.POST,"api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/v1/users/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .anyRequest().authenticated());

        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable CSRF
        httpSecurity.csrf(token -> token.disable());
        // change to stateless
        httpSecurity.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN").build();

        UserDetails userEditor = User.builder()
                .username("editor")
                .password(passwordEncoder.encode("editor"))
                .roles("USER", "EDITOR").build();

        manager.createUser(userAdmin);
        manager.createUser(userEditor);
        return manager;
    }

}
