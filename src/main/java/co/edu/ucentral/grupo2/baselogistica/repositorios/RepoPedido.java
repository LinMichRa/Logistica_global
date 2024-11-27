package co.edu.ucentral.grupo2.baselogistica.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;

@Repository
public interface RepoPedido extends  JpaRepository <pedido,Integer> {
    @Query("SELECT COUNT(p) FROM pedido p WHERE p.conductor IS NULL")
    Long contarPedidosSinConductor();

    // Obtener lista de pedidos sin conductor asignado
    @Query("SELECT p FROM pedido p WHERE p.conductor IS NULL")
    List<pedido> obtenerPedidosSinConductor();

    Optional<pedido> findById(long id);

    @Query("SELECT p FROM pedido p WHERE p.conductor IS NULL AND p.admin.cedula=?1")
    List<pedido> findByAdmin(Long adminId);
}
