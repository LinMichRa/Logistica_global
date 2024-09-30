package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoPedido;

@Service
public class Pedidos {
    @Autowired
    private RepoPedido pedidos;
    
    public pedido guardaPedido (pedido pedido){
        return pedidos.save(pedido);
    }
}
