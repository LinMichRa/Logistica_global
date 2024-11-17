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

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;

@Component
public class JwtAuthenticationProviderDespachador {
    @Value("${jwt.secret}")
    private String secretKey;

    private HashMap<String, despachador> listToken = new HashMap<>();

    //Crear Token
    public String createToken(despachador despachadorJwt){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String tokenCreated = JWT.create()
            .withClaim("cedula", despachadorJwt.getCedula())
            .withClaim("nombre", despachadorJwt.getNombre())
            .withClaim("tipo_documento", despachadorJwt.getTipo_documento())
            .withClaim("direccionBodega", despachadorJwt.getDireccionBodega())
            .withClaim("correo", despachadorJwt.getCorreo())
            .withClaim("rol", despachadorJwt.getRol())
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .sign(algorithm);

        listToken.put(tokenCreated, despachadorJwt);
        return tokenCreated;
    }

    public Authentication validateToken(String token) throws AuthenticationException {

        System.out.println("entre tambien aqui");
        System.out.println(token);

        //verifica el token como su firma y expiración, lanza una excepcion si algo falla
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);


        despachador exists = listToken.get(token);
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
