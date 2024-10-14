package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoCliente;

@Service
public class SerCliente {
    @Autowired
    private RepoCliente Clientes;

    public cliente guardarCliente(cliente cliente){
        cliente.setContraseña(String.valueOf(cliente.getCedula()));
        return Clientes.save(cliente);
    }

    public cliente modficarCliente(cliente Cliente){
        cliente clienteExistente = Clientes.findById(Cliente.getCedula()).orElse(null);
        if (clienteExistente == null){
            throw new RuntimeException("Cliente con esta cedula"+Cliente.getCedula()+"no fue encontrado");
        }
        clienteExistente.setCedula(Cliente.getCedula());
        clienteExistente.setCorreo(Cliente.getCorreo());
        clienteExistente.setContraseña(Cliente.getContraseña());
        clienteExistente.setPedidos(Cliente.getPedidos());
        clienteExistente.setTipo_documento(Cliente.getTipo_documento());
        clienteExistente.setNombre(Cliente.getNombre());

        return Clientes.save(clienteExistente);
    }

    public List<cliente> buscarCliente(){
        return Clientes.findAll();
    }
}
