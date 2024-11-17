package co.edu.ucentral.grupo2.baselogistica.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.security.Roles;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerConductor;
import co.edu.ucentral.grupo2.baselogistica.useCase.IAuthUseCaseConductor;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/conductor")
public class Controconductor {
    @Autowired
    private SerConductor conductorServicio;

    private final IAuthUseCaseConductor iAuthUseCase;

    private final PasswordEncoder passwordEncoder;

    //Definicion enrutamiento registrar conductor
    @PostMapping("/registrarConductor")
    public ResponseEntity<conductor> guardarConductor(@ModelAttribute conductor conductor){
        String password = conductor.getContraseña();
        conductor.setContraseña(passwordEncoder.encode(password));
        conductor.setRol(Roles.CONDUCTOR);
        conductor conductorGuardado = conductorServicio.guardarConductor(conductor);
        return new ResponseEntity<>(conductorGuardado, HttpStatus.CREATED);
    }
    
    @PostMapping("/modificarCoductor/{cedula}")
    public ResponseEntity<conductor>modificarVehiculo(@PathVariable("cedula") Long cedula, @ModelAttribute conductor conductor){
        conductor conductorModificado = conductorServicio.modificarConductor(conductor);
        
        return new ResponseEntity<>(conductorModificado, HttpStatus.OK);
    }

    @GetMapping("/mostrarConductor")
    public ResponseEntity<List<conductor>> buscarConductor() {
        List<conductor> conductor = conductorServicio.buscarConductor();
        return ResponseEntity.ok(conductor);
    }

    @GetMapping("/buscarConductorPorCedula/{cedula}")
    public ResponseEntity<?> buscarConductorPorCedula(@PathVariable("cedula") long cedula){
        Optional <conductor> conductorOptional= conductorServicio.buscarConductorPorCedula(cedula);

        if(conductorOptional.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(conductorOptional.get());
    }
}
