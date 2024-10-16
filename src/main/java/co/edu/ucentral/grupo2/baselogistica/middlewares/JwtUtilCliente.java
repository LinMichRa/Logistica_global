package co.edu.ucentral.grupo2.baselogistica.middlewares;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtilCliente {

    @Autowired
    private EnvConfig envConfig;

    // Generar el token
    public String generateToken(cliente cliente) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("cedula", cliente.getCedula());
        claims.put("nombre", cliente.getNombre());
        return createToken(claims, cliente.getCedula().toString());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String secret = envConfig.getSecretKey();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Validar si el token está activo
    public Boolean validateToken(String token, cliente cliente) {
        final String cedula = extractCedula(token);
        return (cedula.equals(cliente.getCedula().toString()) && !isTokenExpired(token));
    }

    // Extraer cedula del token
    public String extractCedula(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todas las claims
    private Claims extractAllClaims(String token) {
        String secret = envConfig.getSecretKey();
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Verificar si el token ha expirado
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    // Extraer fecha de expiración
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
