package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.domain.ICliente;
import co.edu.ucentral.grupo2.baselogistica.dto.AuthClienteDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;
import co.edu.ucentral.grupo2.baselogistica.exception.CustomerNotExistException;
import co.edu.ucentral.grupo2.baselogistica.exception.PasswordIncorrectException;
import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.security.JwtAuthenticationProvider;
import co.edu.ucentral.grupo2.baselogistica.useCase.IAuthUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
//Se encarga del logueo de un usuario, se usa BCryptPasswordEncoder
public class AuthService implements IAuthUseCase{
    private final ICliente repoCliente;

    //clase que administra los JWTs
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    //clase que encripta contraseñas
    private final PasswordEncoder passwordEncoder;

    //devuelve jwt del CLIENTE dadas unas credenciales
    public JwtResponseDto signIn(AuthClienteDto authClienteDto){
        Optional<cliente> cliente = repoCliente.getClienteByEmail(authClienteDto.getEmail());

        if(cliente.isEmpty()){
            throw new CustomerNotExistException();
        }

        if(!passwordEncoder.matches(authClienteDto.getPassword(), cliente.get().getContraseña())){
            throw new PasswordIncorrectException();
        }

        return new JwtResponseDto(jwtAuthenticationProvider.createToken(cliente.get()));
    }

    //Cierra ssesion eliminando de la lista blanca el token
    public JwtResponseDto signOut(String token){
        String[] authElements= token.split(" ");
        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}
