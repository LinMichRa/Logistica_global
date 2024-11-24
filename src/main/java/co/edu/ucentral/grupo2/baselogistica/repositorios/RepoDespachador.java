package co.edu.ucentral.grupo2.baselogistica.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;

@Repository
public interface RepoDespachador extends  JpaRepository <despachador,Long> {
    Optional<despachador> findByCorreo(String correo);

    Optional<despachador> findByCedula(Long cedula);
}
