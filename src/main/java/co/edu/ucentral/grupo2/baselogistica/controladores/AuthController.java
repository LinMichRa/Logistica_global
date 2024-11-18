package co.edu.ucentral.grupo2.baselogistica.controladores;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucentral.grupo2.baselogistica.dto.AuthDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;
import co.edu.ucentral.grupo2.baselogistica.useCase.IAuthUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthUseCase iAuthUseCase;

    @PostMapping(path="/sign-in")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthDto authClienteDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authClienteDto));
    }
    
    @PostMapping(path="/sign-out")
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(iAuthUseCase.signOut(jwt));
    }
}
