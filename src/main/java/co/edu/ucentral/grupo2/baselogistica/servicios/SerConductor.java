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

    public conductor modificarConductor (conductor Conductor){
        conductor conductorExistente = Conductores.findById(Conductor.getCedula()).orElse(null);
        if(conductorExistente == null) {
            throw new RuntimeException("El conductor que desea buscar con esta cedula"+Conductor.getCedula()+"no se encuentra");
        }
        conductorExistente.setPedidos(Conductor.getPedidos());
        conductorExistente.setZona(Conductor.getZona());
        conductorExistente.setLicencia(Conductor.getLicencia());
        conductorExistente.setVehiculo(Conductor.getVehiculo());

        return Conductores.save(conductorExistente);
    }
}
