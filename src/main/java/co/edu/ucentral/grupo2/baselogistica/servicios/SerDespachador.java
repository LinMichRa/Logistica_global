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
}