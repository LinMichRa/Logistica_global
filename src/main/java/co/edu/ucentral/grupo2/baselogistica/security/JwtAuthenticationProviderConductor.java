package co.edu.ucentral.grupo2.baselogistica.security;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;

@Component
public class JwtAuthenticationProviderConductor {
    @Value("${jwt.secret}")
    private String secretKey;

    private HashMap<String, conductor> listToken = new HashMap<>();


    //Crear Token
    public String createToken(conductor conductorJwt){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String tokenCreated = JWT.create()
            .withClaim("cedula", conductorJwt.getCedula())
            .withClaim("nombre", conductorJwt.getNombre())
            .withClaim("tipo_documento", conductorJwt.getTipo_documento())
            .withClaim("licencia", conductorJwt.getLicencia())
            .withClaim("correo", conductorJwt.getCorreo())
            .withClaim("rol", conductorJwt.getRol())
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .sign(algorithm);

        listToken.put(tokenCreated, conductorJwt);
        return tokenCreated;
    }

    public Authentication validateToken(String token) throws AuthenticationException {

        System.out.println("entre tambien aqui");
        System.out.println(token);

        //verifica el token como su firma y expiración, lanza una excepcion si algo falla
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);


        conductor exists = listToken.get(token);
        if (exists == null) {
            throw new BadCredentialsException("Usuario no registrado.");
        }

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_"+exists.getRol()));

        return new UsernamePasswordAuthenticationToken(exists, token, rolesAndAuthorities);
    }


    //Cerrar sesión, es decir se elimina el Token
    public String deleteToken(String jwt) {

        if (!listToken.containsKey(jwt)) {
            return "No existe token";
        }

        listToken.remove(jwt);
        return "Sesión cerrada exitosamente";
    }
}
