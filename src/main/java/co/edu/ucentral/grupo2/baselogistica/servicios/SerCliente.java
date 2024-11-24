package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.domain.ICliente;
import co.edu.ucentral.grupo2.baselogistica.modelos.cliente;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoCliente;

@Service
public class SerCliente implements ICliente {
    @Autowired
    private RepoCliente Clientes;

    public cliente guardarCliente(cliente cliente) {
        return Clientes.save(cliente);
    }

    public cliente modficarCliente(cliente Cliente) {
        cliente clienteExistente = Clientes.findById(Cliente.getCedula()).orElse(null);
        if (clienteExistente == null) {
            throw new RuntimeException("Cliente con esta cedula " + Cliente.getCedula() + " no fue encontrado");
        }
        clienteExistente.setCedula(Cliente.getCedula());
        clienteExistente.setCorreo(Cliente.getCorreo());
        clienteExistente.setContrasena(Cliente.getContrasena());
        clienteExistente.setPedidos(Cliente.getPedidos());
        clienteExistente.setTipo_documento(Cliente.getTipo_documento());
        clienteExistente.setNombre(Cliente.getNombre());
        return Clientes.save(clienteExistente);
    }

    public List<cliente> buscarCliente() {
        return Clientes.findAll();
    }

    public Optional<cliente> buscarClienteporID(Long cedula) {
        return Clientes.findById(cedula);
    }

    // Implementación de los métodos de ICliente
    @Override
    public List<cliente> getAll() {
        return buscarCliente(); // Llama al método local
    }

    @Override
    public Optional<cliente> getClienteByCedula(String cedula) {
        return buscarClienteporID(Long.valueOf(cedula)); // Llama al método local
    }

    @Override
    public Optional<cliente> getClienteByEmail(String email) {
        return Clientes.findByCorreo(email);
    }

    @Override
    public void delete(String cedula) {
        Clientes.deleteById(Long.valueOf(cedula));
    }
}