package co.edu.ucentral.grupo2.baselogistica.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;



@Repository
public interface RepoCliente extends  JpaRepository <cliente,Long> {
    Optional<cliente> findByCorreo(String correo);
}
