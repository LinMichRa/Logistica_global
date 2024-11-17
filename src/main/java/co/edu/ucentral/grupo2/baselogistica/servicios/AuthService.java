package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.domain.ICliente;
import co.edu.ucentral.grupo2.baselogistica.domain.IConductor;
import co.edu.ucentral.grupo2.baselogistica.domain.IDespachador;
import co.edu.ucentral.grupo2.baselogistica.dto.AuthDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;
import co.edu.ucentral.grupo2.baselogistica.exception.CustomerNotExistException;
import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProvider;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProviderConductor;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProviderDespachador;
import co.edu.ucentral.grupo2.baselogistica.useCase.IAuthUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
//Se encarga del logueo de un usuario, se usa BCryptPasswordEncoder
public class AuthService implements IAuthUseCase{
    private final ICliente repoCliente;

    private final IConductor repoConductor;

    private final IDespachador repoDespachador;

    //clase que administra los JWTs
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final JwtAuthenticationProviderConductor jwtAuthenticationProviderConductor;

    private final JwtAuthenticationProviderDespachador jwtAuthenticationProviderDespachador;

    //clase que encripta contraseñas
    private final PasswordEncoder passwordEncoder;

    //devuelve jwt del CLIENTE dadas unas credenciales
    public JwtResponseDto signIn(AuthDto authDto) {
    String email = authDto.getEmail();
    String password = authDto.getPassword();
    
    // Verificar si el usuario es un cliente
    Optional<cliente> clienteOpt = repoCliente.getClienteByEmail(email);
    if (clienteOpt.isPresent() && passwordEncoder.matches(password, clienteOpt.get().getContraseña())) {
        System.out.println("soy cliente");
        return new JwtResponseDto(jwtAuthenticationProvider.createToken(clienteOpt.get()));
    }

    // Verificar si el usuario es un conductor
    Optional<conductor> conductorOpt = repoConductor.getConductorByEmail(email);
    if (conductorOpt.isPresent() && passwordEncoder.matches(password, conductorOpt.get().getContraseña())) {
        System.out.println("soy conductor");
        return new JwtResponseDto(jwtAuthenticationProviderConductor.createToken(conductorOpt.get()));
    }

    // Verificar si el usuario es un despachador
    Optional<despachador> despachadorOpt = repoDespachador.getDespachadorByEmail(email);
    if (despachadorOpt.isPresent() && passwordEncoder.matches(password, despachadorOpt.get().getContraseña())) {
        System.out.println("soy despachador");
        return new JwtResponseDto(jwtAuthenticationProviderDespachador.createToken(despachadorOpt.get()));
    }

    // Si no coincide con ningún usuario
    throw new CustomerNotExistException();
}


    //Cierra ssesion eliminando de la lista blanca el token
    public JwtResponseDto signOut(String token){
        String[] authElements= token.split(" ");
        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}
