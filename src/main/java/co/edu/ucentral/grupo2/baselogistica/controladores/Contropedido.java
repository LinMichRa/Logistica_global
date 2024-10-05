package co.edu.ucentral.grupo2.baselogistica.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerPedidos;


@RestController
@RequestMapping("api/pedidos")
public class Contropedido {
    @Autowired
    private SerPedidos pedidosServicio;

    //Defincion enrutamiento registrar pedido
    @PostMapping("/registroPedido")  
    public ResponseEntity<pedido> guardarPedido (@ModelAttribute pedido pedido){
        pedido pedidoGuardado = pedidosServicio.guardaPedido(pedido);
        return new ResponseEntity<>(pedidoGuardado, HttpStatus.CREATED);
    }
}
