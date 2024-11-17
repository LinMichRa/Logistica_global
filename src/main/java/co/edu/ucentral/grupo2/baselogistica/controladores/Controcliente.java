package co.edu.ucentral.grupo2.baselogistica.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucentral.grupo2.baselogistica.dto.AuthClienteDto;
import co.edu.ucentral.grupo2.baselogistica.dto.JwtResponseDto;
import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.security.Roles;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerCliente;
import co.edu.ucentral.grupo2.baselogistica.useCase.IAuthUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class Controcliente {
    @Autowired
    private SerCliente clienteServicio;
    
    private final IAuthUseCase iAuthUseCase;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registrarCliente")
    public ResponseEntity<cliente> guardarCliente(@ModelAttribute cliente cliente){
        String password = cliente.getContraseña();
        cliente.setContraseña(passwordEncoder.encode(password));
        cliente.setRol(Roles.CLIENTE);
        cliente clienteGuardado = clienteServicio.guardarCliente(cliente);
        return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);
    }
    
    @PostMapping("/modificarCliente/{cedula}")
    public ResponseEntity<cliente>modificarCliente(@PathVariable("cedula") Long cedula, @ModelAttribute cliente cliente){
        cliente clienteModificado = clienteServicio.modficarCliente(cliente);

        return new ResponseEntity<>(clienteModificado, HttpStatus.OK);
    }

    @GetMapping("/mostrarCliente")
    public ResponseEntity<List<cliente>> buscarCliente() {
        List<cliente> cliente = clienteServicio.buscarCliente();
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/buscarClientePorCedula/{cedula}")
    public ResponseEntity<?> buscarClientePorID(@PathVariable("cedula") long cedula){
        Optional <cliente> clienteOptional = clienteServicio.buscarClienteporID(cedula);

        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteOptional.get());
    }

    @PostMapping(path="/sign-in")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthClienteDto authClienteDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authClienteDto));
    }
    
    @PostMapping(path="/sign-out")
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(iAuthUseCase.signOut(jwt));
    }

    /*@PostMapping("/login")
    public ResponseEntity<JwtResponseDto> loginCliente(@RequestBody cliente cliente) {
    JwtResponseDto jwtResponse = iAuthUseCase.login(cliente);
    return ResponseEntity.ok(jwtResponse);
}*/
}
