package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoDespachador;

@Service
public class SerDespachador {

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
        despachadorExistente.setDireccionBodega(Despachador.getDireccionBodega());
        despachadorExistente.setCedula(Despachador.getCedula());
        despachadorExistente.setNombre(Despachador.getNombre());
        return Despachadores.save(despachadorExistente);
    }
}