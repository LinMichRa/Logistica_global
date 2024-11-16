package co.edu.ucentral.grupo2.baselogistica.useCase;

import java.util.List;
import java.util.Optional;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;

//Interfaz de servicio de cliente
public interface IClienteUseCase {

    //Devuelve lista con todos los clientes
    List <cliente> getAll();

    Optional<cliente> getClienteById(String cedula);

    Optional<cliente> getClienteByEmail(String email);

    void delt(String cedula);
}
    

