package co.edu.ucentral.grupo2.baselogistica.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;

@Repository
public interface RepoConductor extends  JpaRepository <conductor,Long> {
    Optional<conductor> findByCorreo(String correo);
}
