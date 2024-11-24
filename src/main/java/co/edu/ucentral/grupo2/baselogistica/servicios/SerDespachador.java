package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.domain.IDespachador;
import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoDespachador;

@Service
public class SerDespachador implements IDespachador{

    @Autowired
    private RepoDespachador Despachadores;

    public despachador guardarDespachador(despachador despachador){
        return Despachadores.save(despachador);
    }

    public despachador modificarDespachador(despachador Despachador) {
        despachador despachadorExistente = Despachadores.findById(Despachador.getCedula()).orElse(null);
        if(despachadorExistente==null){
            throw new RuntimeException("El despachador con cedula"+Despachador.getCedula()+"no se encuetra.");
        }
        despachadorExistente.setCedula(Despachador.getCedula());
        despachadorExistente.setNombre(Despachador.getNombre());
        despachadorExistente.setBodega(Despachador.getBodega());
        despachadorExistente.setCorreo(Despachador.getCorreo());
        despachadorExistente.setContrasena(Despachador.getContrasena());
        return Despachadores.save(despachadorExistente);
    }

    public List<despachador> buscarDespachador(){
        return Despachadores.findAll();
    }

    public Optional<despachador> buscarDespachadorPorCedula( Long cedula){
        return Despachadores.findById(cedula);
    }

    @Override
    public List<despachador> getAll() {
        return buscarDespachador(); // Llama al método local
    }

    @Override
    public Optional<despachador> getDespachadorByCedula(String cedula) {
        return buscarDespachadorPorCedula(Long.valueOf(cedula)); // Llama al método local
    }

    @Override
    public Optional<despachador> getDespachadorByEmail(String email) {
        return Despachadores.findByCorreo(email);
    }

    @Override
    public void delete(String cedula) {
        Despachadores.deleteById(Long.valueOf(cedula));
    }
}