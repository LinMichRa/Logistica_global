package co.edu.ucentral.grupo2.baselogistica.controladores;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerConductor;

@RestController
@RequestMapping("/api/conductor")
public class Controconductor {
    @Autowired
    private SerConductor conductorServicio;

    //Definicion enrutamiento registrar conductor
    @PostMapping("/registrarConductor")
    public ResponseEntity<conductor> guardarConductor(@ModelAttribute conductor conductor){
        conductor conductorGuardado = conductorServicio.guardarConductor(conductor);
        return new ResponseEntity<>(conductorGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/modificarCoductor/{cedula}")
    public ResponseEntity<conductor>modificarVehiculo(@PathVariable("cedula") Long cedula, @ModelAttribute conductor conductor){
        conductor conductorModificado = conductorServicio.modificarConductor(conductor);

        return new ResponseEntity<>(conductorModificado, HttpStatus.OK);
    }

}