package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoConductor;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoPedido;

@Service
public class SerPedidos {
    @Autowired
    private RepoPedido pedidos;

    @Autowired
    private RepoConductor conductorRepository;
    
    @Autowired
    private RepoPedido pedidoRepository;
    
    public pedido guardaPedido (pedido pedido){
        return pedidos.save(pedido);
    }

    public pedido modificarPedido(pedido Pedido){
        pedido pedidoExistente = pedidos.findById(Pedido.getId()).orElse(null);
        if(pedidoExistente==null) {
            throw new RuntimeException("Pedido con la ID"+Pedido.getId()+"no fue encontrado");
        }
        pedidoExistente.setConductor(Pedido.getConductor());
        pedidoExistente.setAdmin(Pedido.getAdmin());
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

    // Contar pedidos sin conductor
    public Long contarPedidosSinConductor() {
        return pedidos.contarPedidosSinConductor();
    }

    // Obtener lista de pedidos sin conductor
    public List<pedido> obtenerPedidosSinConductor() {
        return pedidos.obtenerPedidosSinConductor();
    }

    // Asignar un conductor a un pedido
    public void asignarConductor(Long pedidoId, Long conductorId) throws Exception {
        Optional<pedido> pedidoOptional = pedidos.findById(pedidoId);
        Optional<conductor> conductorOptional = conductorRepository.findById(conductorId);

        if (pedidoOptional.isEmpty()) {
            throw new Exception("Pedido no encontrado");
        }
        if (conductorOptional.isEmpty()) {
            throw new Exception("Conductor no encontrado");
        }

        pedido pedido = pedidoOptional.get();
        conductor conductor = conductorOptional.get();

        // Asignar conductor al pedido
        pedido.setConductor(conductor);

        // Guardar cambios en la base de datos
        pedidoRepository.save(pedido);
    }

    public List<pedido> obtenerPedidosPorAdmin(Long adminId) {
        return pedidoRepository.findByAdmin(adminId);
    }
}

