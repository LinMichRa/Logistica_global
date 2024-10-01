package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoConductor;

@Service
public class SerConductor {
    @Autowired
    private RepoConductor Conductores;

    public conductor guardarConductor (conductor conductor){
        return Conductores.save(conductor);
    }
}
