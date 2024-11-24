package co.edu.ucentral.grupo2.baselogistica.modelos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "despachadores")
@Builder

public class despachador{
    @Id
    @Column(name="cedula", nullable =false, unique = true )
    private Long cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="direccionBodega") //1 direccion default
    private String bodega;
    
    @Column(name="correo")
    private String correo;

    @Column(name="contraseña")
    private String contrasena;

    @Column(name="rol")
    private String rol;

}