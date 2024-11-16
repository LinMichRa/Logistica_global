package co.edu.ucentral.grupo2.baselogistica.modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "conductores")
@Builder

public class conductor{
    @Id
    @Column(name="cedula", nullable =false )
    private Long cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="licencia")
    private int licencia;

    @Column(name="zona")
    private String zona;

    @Column(name="correo")
    private String correo;

    @Column(name="contraseña")
    private String contraseña;
    
    @Column(name="rol", nullable = false)
    private String rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private vehiculo vehiculo;

    @OneToMany(mappedBy = "conductor")
    private List<pedido> pedidos;
}