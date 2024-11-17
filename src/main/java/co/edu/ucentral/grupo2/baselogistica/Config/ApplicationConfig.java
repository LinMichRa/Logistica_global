package co.edu.ucentral.grupo2.baselogistica.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthFilter;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig{

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtAuthenticationProvider);
    }
    
}
