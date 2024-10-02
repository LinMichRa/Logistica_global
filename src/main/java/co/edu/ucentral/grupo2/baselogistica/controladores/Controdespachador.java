package co.edu.ucentral.grupo2.baselogistica.controladores;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerDespachador;


@RestController
@RequestMapping("/api/despachador")
public class Controdespachador {
    @Autowired
    private SerDespachador despachadorServicio;

    //Definicion enrutamiento registrar despachador
    @PostMapping("/registroDespachador")
    public ResponseEntity<despachador> guardarDespachador(@ModelAttribute despachador despachador){
        despachador despachadorGuardado = despachadorServicio.guardarDespachador(despachador);
        return new ResponseEntity<>(despachadorGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/modificarDespachador/{cedula}")
    public ResponseEntity<despachador>modificarDespachador(@PathVariable("cedula") Long cedula, @ModelAttribute despachador despachador){
        despachador despachadorModificado = despachadorServicio.modificarDespachador(despachador);

        return new ResponseEntity<>(despachadorModificado, HttpStatus.OK);
    }

}