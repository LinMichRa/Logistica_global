package co.edu.ucentral.grupo2.baselogistica.useCase;

import co.edu.ucentral.grupo2.baselogistica.dto.AuthDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;

public interface IAuthUseCase {

    //CLIENTE
    JwtResponseDto signIn(AuthDto authCliebteDto);
    
    JwtResponseDto signOut(String jwt);
}