package co.edu.ucentral.grupo2.baselogistica.servicios;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepositoriosVehiculos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Vehiculos {
@Autowired
    RepositoriosVehiculos repositoriosVehiculos;
@Override
    public vehiculo crear(vehiculo vehiculo){return repositoriosVehiculos.save(vehiculo); }

}
