package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoCliente;

@Service
public class Cliente {
    @Autowired
    private RepoCliente Clientes;

    public cliente guardarCliente(cliente cliente){
        return Clientes.save(cliente);
    }
}
