package co.edu.ucentral.grupo2.baselogistica.servicios;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepositoriosVehiculos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerVehiculos {
@Autowired
    RepositoriosVehiculos repositoriosVehiculos;

    public vehiculo guardarVehiculo(vehiculo vehiculo){return repositoriosVehiculos.save(vehiculo); }

    public vehiculo modificarVehiculo(vehiculo vehiculo){
        vehiculo vehiculoExistente = repositoriosVehiculos.findById(vehiculo.getId()).orElse(null);
        if (vehiculoExistente == null){
            throw new RuntimeException("Vehiculo con este ID"+ vehiculo.getId() + "No fue encontrado.");

        }
        vehiculoExistente.setPlaca(vehiculo.getPlaca());
        vehiculoExistente.setMarca(vehiculo.getMarca());

        return repositoriosVehiculos.save(vehiculoExistente);
    }
}
