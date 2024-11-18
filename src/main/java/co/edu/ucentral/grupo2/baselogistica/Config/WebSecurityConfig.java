package co.edu.ucentral.grupo2.baselogistica.Config;

import static org.springframework.security.config.Customizer.*;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import co.edu.ucentral.grupo2.baselogistica.exception.AccessDeniedHandlerException;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthFilter;
import co.edu.ucentral.grupo2.baselogistica.security.Roles;

/**
 * Clase que configura lo relacionado a las peticiones HTTP
 */
@Configuration
@EnableWebSecurity

public class WebSecurityConfig {
    @Autowired
    private final AccessDeniedHandlerException accessDeniedHandlerException;

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;

    // Constructor para inyectar dependencias
    public WebSecurityConfig(AccessDeniedHandlerException accessDeniedHandlerException, JwtAuthFilter jwtAuthFilter) {
        this.accessDeniedHandlerException = accessDeniedHandlerException;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    
    /**
     * Configura la seguridad de las peticiones HTTP
     * @param http Peticion a configurar
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                //.exceptionHandling(handling -> handling.accessDeniedHandler(accessDeniedHandlerException))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/auth/sign-in","/auth/sign-out","/api/cliente/registrarCliente",
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**","api/conductor/registrarConductor",
                                "/api/conductor/sign-in","/api/conductor/sign-out","api/despachador/registroDespachador").permitAll()
                                //.requestMatchers(HttpMethod.GET, "/customers").hasAnyRole(Roles.CUSTOMER, Roles.ADMIN)
                                .requestMatchers(HttpMethod.GET, "/cliente/**").hasAnyRole(Roles.CLIENTE, Roles.CONDUCTOR, Roles.ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/cliente/**").hasRole(Roles.ADMIN)
                                //.requestMatchers(HttpMethod.DELETE, "/customers/**").hasAuthority("ELIMINAR_PRIVILEGE")

                                .requestMatchers(HttpMethod.GET, "/pedidos/**").hasAnyRole(Roles.CLIENTE, Roles.ADMIN)
                                //.requestMatchers(HttpMethod.POST, "/pedidos/**").hasRole(Roles.ADMIN)
                                //.requestMatchers("/pedidos").hasAuthority("COMPRAR_PRIVILEGE")
                                //.requestMatchers("/customers").hasRole(Roles.ADMIN)

                                //solo toma el primer filtro, ya no se puede anidar un rol con una autoridad

                                //hasAuthority o hasRole para un solo rol/autoridad
                                //hasAnyAuthority para varios roles
                                //.anyRequest().authenticated()

                );
        return http.build();
    }

    @Bean
CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

}
