package co.edu.ucentral.grupo2.baselogistica.controladores;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerCliente;


@RestController
@RequestMapping("/api/cliente")
public class Controcliente {
    @Autowired
    private SerCliente clienteServicio;

    @PostMapping("/registrarCliente")
    public ResponseEntity<cliente> guardarCliente(@ModelAttribute cliente cliente){
        cliente clienteGuardado = clienteServicio.guardarCliente(cliente);
        return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/modificarCliente/{cedula}")
    public ResponseEntity<cliente>modificarCliente(@PathVariable("cedula") Long cedula, @ModelAttribute cliente cliente){
        cliente clienteModificado = clienteServicio.modficarCliente(cliente);

        return new ResponseEntity<>(clienteModificado, HttpStatus.OK);
    }

}