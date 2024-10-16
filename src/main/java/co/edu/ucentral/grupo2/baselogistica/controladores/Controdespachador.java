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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/mostrarDespachador")
    public ResponseEntity<List<despachador>> buscarDespachador() {
        List<despachador> despachador = despachadorServicio.buscarDespachador();
        return ResponseEntity.ok(despachador);
    }
    @GetMapping("/buscarDespachadorPorCedula/{cedula}")
    public ResponseEntity<?> buscarDespachadorPorCedula(@PathVariable("cedula") long cedula){
        Optional <despachador> despachadorOptional = despachadorServicio.buscarDespachadorPorCedula(cedula);

        if(despachadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(despachadorOptional.get());
    }
}
