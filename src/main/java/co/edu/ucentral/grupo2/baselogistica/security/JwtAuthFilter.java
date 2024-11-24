package co.edu.ucentral.grupo2.baselogistica.security;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.ucentral.grupo2.baselogistica.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Lista blanca de URIs
     */
    private List<String> urlsToSkip = List.of(
    "/auth/sign-in",
    "/auth/sign-out",
    "/swagger-ui.html",
    "/swagger-ui",
    "/api-docs",
    "/api/despachador/modificarDespachador/**",
    "/api/conductor/**",
    "/api/despachador/registroDespachador",
    "/api/vehiculos/registrarVehiculo",
    "/api/conductor/registrarConductor",
    "/api/pedidos/registroPedido",
    "/api/despachador/buscarDespachadorPorCedula/**",
    "/api/vehiculos/mostrarVehiculo",
    "/api/vehiculos/buscarVehiculoPorID/**"
);
 //eliminar url's no permmit all

 @Override
 protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
  String requestUri = request.getRequestURI();
  System.out.println("Evaluando URI: " + requestUri);

  return urlsToSkip.stream().anyMatch(url -> {
      if (url.endsWith("/**")) {
          String baseUrl = url.replace("/**", "");
          return requestUri.startsWith(baseUrl);
      }
      return requestUri.equals(url);
  });
 }
 


    /**
     * Valida si la petición contiene la cabezera de authorization con el bearer token
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * @throws UnauthorizedException - Si no tiene la cabezera HttpHeaders.AUTHORIZATION
     *                               - Si tiene más de dos elementos en al cabezera o no tiene 'Bearer'
     *                               - Si el token no es valido
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        /*
        System.out.println("headers");
        System.out.println(header);
        System.out.println(request);


        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("header and value");
            System.out.println(headerName);
            System.out.println(headerValue);
            }
          */

        if (header == null) {
            throw new UnauthorizedException();
        }

        String[] authElements = header.split(" ");

        if (authElements.length != 2 || !"Bearer".equals(authElements[0])) {
            throw new UnauthorizedException();
        }

        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(authElements[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("voy a imprimir el context");
            System.out.println(SecurityContextHolder.getContext());
            System.out.println("voy a imprimir la autenticacion");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            System.out.println("se estalló");
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("llegué aqui");

        filterChain.doFilter(request, response);
    }

    
}