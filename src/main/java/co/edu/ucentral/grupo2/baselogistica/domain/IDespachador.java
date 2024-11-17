package co.edu.ucentral.grupo2.baselogistica.domain;

import java.util.List;
import java.util.Optional;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;

public interface IDespachador {
    List<despachador> getAll();

    Optional<despachador> getDespachadorByCedula(String cedula);

    Optional<despachador> getDespachadorByEmail(String email);

    void delete(String cedula);
}
