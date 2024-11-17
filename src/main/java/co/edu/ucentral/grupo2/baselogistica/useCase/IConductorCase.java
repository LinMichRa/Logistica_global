package co.edu.ucentral.grupo2.baselogistica.useCase;

import java.util.List;
import java.util.Optional;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;

public interface IConductorCase {
    List<conductor> getAll();

    Optional<conductor> getConductorByCedula(String cedula);

    Optional<conductor> getConductorByEmail(String email);

    void delete(String cedula);
}
