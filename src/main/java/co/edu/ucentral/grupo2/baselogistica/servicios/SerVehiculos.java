package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoVehiculos;

@Service
public class SerVehiculos{

    @Autowired
    private RepoVehiculos Vehiculos;

    public vehiculo guardarVehiculo(vehiculo vehiculo){
        return Vehiculos.save(vehiculo);
    }

    public vehiculo modificarVehiculo(vehiculo vehiculo){
        vehiculo vehiculoExistente = Vehiculos.findById(vehiculo.getId()).orElse(null);
        if (vehiculoExistente == null){
            throw new RuntimeException("Vehiculo con este ID"+ vehiculo.getId() + "No fue encontrado.");

        }
        vehiculoExistente.setPlaca(vehiculo.getPlaca());
        vehiculoExistente.setMarca(vehiculo.getMarca());
        vehiculoExistente.setId(vehiculo.getId());
        vehiculoExistente.setCap_peso(vehiculo.getCap_peso());


        return Vehiculos.save(vehiculoExistente);
    }

    public List<vehiculo> buscarVehiculo(){
        return Vehiculos.findAll();
    }

}
