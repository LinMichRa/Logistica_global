package co.edu.ucentral.grupo2.baselogistica.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerVehiculos;


@RestController
@RequestMapping("/api/vehiculos")
public class Controvehiculo {

    @Autowired
    private SerVehiculos vehiculoServicio;

    //Definicion enrutamiento registrar vehiculo
    @PostMapping("/registrarVehiculo")
    public ResponseEntity<vehiculo> guardarVehiculo(@ModelAttribute vehiculo vehiculo) {
        vehiculo vehiculoGuardado = vehiculoServicio.guardarVehiculo(vehiculo);
        return new ResponseEntity<>(vehiculoGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/modificarVehiculo")
    public ResponseEntity<vehiculo>modificarVehiculo(@RequestBody vehiculo vehiculo){
        vehiculo vehiculoModificado = vehiculoServicio.modificarVehiculo(vehiculo);

        return new ResponseEntity<>(vehiculoModificado, HttpStatus.OK);
    }
}