package co.edu.ucentral.grupo2.baselogistica.useCase;

import co.edu.ucentral.grupo2.baselogistica.dto.AuthDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;

public interface IAuthUseCaseConductor {
    //CONDUCTOR
    JwtResponseDto signIn(AuthDto authConductorDto);
    
    JwtResponseDto signOut(String jwt);
}
