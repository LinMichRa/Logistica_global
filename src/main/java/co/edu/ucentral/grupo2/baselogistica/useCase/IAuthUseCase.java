package co.edu.ucentral.grupo2.baselogistica.useCase;

import co.edu.ucentral.grupo2.baselogistica.dto.AuthClienteDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;

public interface IAuthUseCase {
    JwtResponseDto signIn(AuthClienteDto authCliebteDto);
    
    JwtResponseDto signOut(String jwt);
}