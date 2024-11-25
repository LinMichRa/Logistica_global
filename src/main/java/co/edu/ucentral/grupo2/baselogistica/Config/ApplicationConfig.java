package co.edu.ucentral.grupo2.baselogistica.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthFilter;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProvider;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProviderConductor;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProviderDespachador;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig{

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final JwtAuthenticationProviderConductor jwtAuthenticationProviderCon;

    private final JwtAuthenticationProviderDespachador jwtAuthenticationProviderDes;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtAuthenticationProvider, jwtAuthenticationProviderCon, jwtAuthenticationProviderDes);
    }
    
}
