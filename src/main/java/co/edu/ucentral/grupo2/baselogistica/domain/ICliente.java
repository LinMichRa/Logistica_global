package co.edu.ucentral.grupo2.baselogistica.domain;

import java.util.List;
import java.util.Optional;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;

public interface ICliente {

    List<cliente> getAll();

    Optional<cliente> getClienteByCedula(String cedula);

    Optional<cliente> getClienteByEmail(String email);

    void delete(String cedula);
}
