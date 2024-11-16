package co.edu.ucentral.grupo2.baselogistica.repositorios;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;

@Repository
public interface RepoConductor extends  JpaRepository <conductor,Long> {
    List<conductor> getAll();

    Optional<conductor> getConductorById(String cedula);

    Optional<conductor> getConductorByEmail(String email);

    void delete(String cedula);
}
