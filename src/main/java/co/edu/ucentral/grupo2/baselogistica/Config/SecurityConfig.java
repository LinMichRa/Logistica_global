package co.edu.ucentral.grupo2.baselogistica.Config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    return http
      .csrf(csrf ->
        csrf
        .disable())
      .authorizeHttpRequests(authRequest ->
        authRequest
          .requestMatchers("/auth/**").permitAll()
          .anyRequest().authenticated()
          )
      .formLogin(withDefaults())
      .build();
  }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .anyRequest().permitAll()  // Permite el acceso sin autenticación
                )
                .csrf(csrf -> csrf.disable())   // Desactiva CSRF para pruebas
                .formLogin(login -> login.disable());  // Desactiva el formulario de login
        return http.build();
    }*/
}

