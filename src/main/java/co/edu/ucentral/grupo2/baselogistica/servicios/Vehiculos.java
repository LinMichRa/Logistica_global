package co.edu.ucentral.grupo2.baselogistica.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepositoriosVehiculos;

@Service
public class Vehiculos{

    @Autowired
    private RepositoriosVehiculos repositoriosVehiculos;

    public vehiculo guardarVehiculo(vehiculo vehiculo){
        return repositoriosVehiculos.save(vehiculo);
    }

}
