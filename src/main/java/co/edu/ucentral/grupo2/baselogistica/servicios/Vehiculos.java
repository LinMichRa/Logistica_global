package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoVehiculos;

@Service
public class Vehiculos{

    @Autowired
    private RepoVehiculos Vehiculos;

    public vehiculo guardarVehiculo(vehiculo vehiculo){
        return Vehiculos.save(vehiculo);
    }

}
