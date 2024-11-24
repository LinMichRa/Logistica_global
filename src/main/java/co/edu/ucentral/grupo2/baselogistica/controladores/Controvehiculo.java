package co.edu.ucentral.grupo2.baselogistica.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/modificarVehiculo/{id}")
    public ResponseEntity<vehiculo>modificarVehiculo(@PathVariable("id") Integer id,@ModelAttribute vehiculo vehiculo){
        vehiculo vehiculoModificado = vehiculoServicio.modificarVehiculo(vehiculo);

        return new ResponseEntity<>(vehiculoModificado, HttpStatus.OK);
    }

    @GetMapping("/mostrarVehiculo")
    public ResponseEntity<List<vehiculo>> buscarVehiculo() {
        List<vehiculo> vehiculo = vehiculoServicio.buscarVehiculo();
        return ResponseEntity.ok(vehiculo);
    } 

    @GetMapping("/buscarVehiculoPorID/{id}")
    public ResponseEntity<?> buscarVehiculoPorID(@PathVariable("id") int id){
        Optional <vehiculo> vehiculoOptional = vehiculoServicio.buscarVehiculoPorID(id);

        if(vehiculoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculoOptional.get());
    }
}
