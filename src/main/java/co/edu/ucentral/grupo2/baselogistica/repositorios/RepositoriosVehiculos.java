package co.edu.ucentral.grupo2.baselogistica.repositorios;

import co.edu.ucentral.grupo2.baselogistica.modelos.vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosVehiculos extends  JpaRepository <vehiculo,Integer > {

}
