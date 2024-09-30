package co.edu.ucentral.grupo2.baselogistica.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerVehiculos;


@RestController
@RequestMapping("/api/vehiculos")
public class Controvehiculo {
    
    @Autowired
    private SerVehiculos vehiculoServicio;

    //Definicion enrutamiento registrar vehiculo
    @PostMapping("/registrarVehiculo")
    public ResponseEntity<vehiculo> guardarVehiculo(@RequestBody vehiculo vehiculo) {
        vehiculo vehiculoGuardado = vehiculoServicio.guardarVehiculo(vehiculo);
        return new ResponseEntity<>(vehiculoGuardado, HttpStatus.CREATED);
    }
}
