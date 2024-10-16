package co.edu.ucentral.grupo2.baselogistica.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .anyRequest().permitAll()  // Permite el acceso sin autenticación
                )
                .csrf(csrf -> csrf.disable())   // Desactiva CSRF para pruebas
                .formLogin(login -> login.disable());  // Desactiva el formulario de login
        return http.build();
    }
}

