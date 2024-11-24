package co.edu.ucentral.grupo2.baselogistica.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.grupo2.baselogistica.domain.IConductor;
import co.edu.ucentral.grupo2.baselogistica.modelos.conductor;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoConductor;

@Service
public class SerConductor implements IConductor{
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
        conductorExistente.setCedula(Conductor.getCedula());
        conductorExistente.setNombre(Conductor.getNombre());
        conductorExistente.setLicencia(Conductor.getLicencia());        conductorExistente.setPedidos(Conductor.getPedidos());
        conductorExistente.setZona(Conductor.getZona());
        conductorExistente.setCorreo(Conductor.getCorreo());
        conductorExistente.setContrasena(Conductor.getContrasena());
        conductorExistente.setVehiculo(Conductor.getVehiculo());
        conductorExistente.setPedidos(Conductor.getPedidos());

        return Conductores.save(conductorExistente);
    }

    public List<conductor> buscarConductor(){
        return Conductores.findAll();
    }

    public Optional<conductor> buscarConductorPorCedula(Long cedula){
        return Conductores.findById(cedula);
    }

    @Override
    public List<conductor> getAll() {
        return Conductores.findAll();
    }

    @Override
    public Optional<conductor> getConductorByCedula(String cedula) {
        return Conductores.findById(Long.valueOf(cedula));
    }

    @Override
    public Optional<conductor> getConductorByEmail(String email) {
        return Conductores.findByCorreo(email);
    }

    @Override
    public void delete(String cedula) {
        Conductores.deleteById(Long.valueOf(cedula));
    }
}
