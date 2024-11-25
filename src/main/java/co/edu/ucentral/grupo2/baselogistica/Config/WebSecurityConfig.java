package co.edu.ucentral.grupo2.baselogistica.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.edu.ucentral.grupo2.baselogistica.exception.AccessDeniedHandlerException;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthFilter;

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
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Permite todo temporalmente
        return http.build();
    }
   /*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
       /*  http
                //.cors(withDefaults())
                //.exceptionHandling(handling -> handling.accessDeniedHandler(accessDeniedHandlerException))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/auth/sign-in","/auth/sign-out",
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**","api/conductor/registrarConductor","api/conductor/**",
                                "/api/conductor/sign-in","/api/conductor/sign-out","api/despachador/modificarDespachador",
                                "/api/despachador/registroDespachador","/api/vehiculos/registrarVehiculo",
                                "/api/conductor/registrarConductor", "/api/pedidos/registroPedido","api/despachador/**",
                                "/buscarDespachadorPorCedula/{cedula}").permitAll()
                                .requestMatchers("/buscarDespachadorPorCedula/**").permitAll()//eliminar url's no permmit all
                                .requestMatchers("/api/despachador/buscarDespachadorPorCedula/**").permitAll()
                                //.requestMatchers(HttpMethod.GET, "/customers").hasAnyRole(Roles.CUSTOMER, Roles.ADMIN)
                                .requestMatchers(HttpMethod.GET, "/cliente/**").hasAnyRole(Roles.CLIENTE, Roles.ADMIN)
                                .requestMatchers(HttpMethod.GET, "/cliente/**","/api/pedidos/pendientes/count","/api/pedidos/pendientes",
                                "/api/pedidos/**").hasRole(Roles.ADMIN)
                                //.requestMatchers(HttpMethod.DELETE, "/customers/**").hasAuthority("ELIMINAR_PRIVILEGE")
                                //.requestMatchers(HttpMethod.GET, "api/despachador/**").hasAuthority(Roles.ADMIN)
                                //.requestMatchers(HttpMethod.POST, "/despachador/**").hasAuthority(Roles.ADMIN)

                                .requestMatchers(HttpMethod.GET, "/pedidos/**").hasAnyRole(Roles.CLIENTE, Roles.CONDUCTOR, Roles.ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasAnyRole(Roles.ADMIN)
                                //.requestMatchers(HttpMethod.POST, "/pedidos/**").hasRole(Roles.ADMIN)
                                //.requestMatchers("/pedidos").hasAuthority("COMPRAR_PRIVILEGE")
                                //.requestMatchers("/customers").hasRole(Roles.ADMIN)

                                //solo toma el primer filtro, ya no se puede anidar un rol con una autoridad

                                //hasAuthority o hasRole para un solo rol/autoridad
                                //hasAnyAuthority para varios roles
                                //.anyRequest().authenticated()

                );
        return http.build();
    }*/

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    // Especificar explícitamente los orígenes permitidos
    configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200", "http://tu-otro-dominio.com"));

    // Métodos HTTP permitidos
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

    // Encabezados permitidos
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

    // Exponer encabezados personalizados si es necesario
    configuration.setExposedHeaders(Arrays.asList("Authorization"));

    // Permitir envío de cookies o credenciales
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}*/


    @Bean
    public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") // Cambia según el dominio del frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    };
}


}
