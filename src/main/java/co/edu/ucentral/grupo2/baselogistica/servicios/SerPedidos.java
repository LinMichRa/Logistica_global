package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoPedido;

@Service
public class SerPedidos {
    @Autowired
    private RepoPedido pedidos;
    
    public pedido guardaPedido (pedido pedido){
        return pedidos.save(pedido);
    }

    public pedido modificarPedido(pedido Pedido){
        pedido pedidoExistente = pedidos.findById(Pedido.getId()).orElse(null);
        if(pedidoExistente==null) {
            throw new RuntimeException("Pedido con la ID"+Pedido.getId()+"no fue encontrado");
        }
        pedidoExistente.setConductor(Pedido.getConductor());
        pedidoExistente.setDespachador(Pedido.getDespachador());
        pedidoExistente.setCiudad(Pedido.getCiudad());
        pedidoExistente.setCliente(Pedido.getCliente());
        pedidoExistente.setLocalidad(Pedido.getLocalidad());
        pedidoExistente.setCt_paquetes(Pedido.getCt_paquetes());
        return pedidos.save(pedidoExistente);
    }

    public List<pedido> buscarPedido(){
        return pedidos.findAll();
    }

    public pedido actualizarFotoPedido(int id, String nombreArchivo) {
        pedido pedido = pedidos.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setFoto(nombreArchivo);
        return pedidos.save(pedido);
    }

    public Optional<pedido> buscarPedidoPorID(int id){
        return pedidos.findById(id);
    }
}
