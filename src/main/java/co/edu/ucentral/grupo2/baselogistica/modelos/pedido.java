package co.edu.ucentral.grupo2.baselogistica.modelos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "pedidos")
@Builder

public class pedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable =false)
    private int id;

    @Column(name="direccion", nullable =false )
    private String direccion;

    @Column(name="localidad", nullable =false )
    private String localidad;

    @Column(name="cd_postal", nullable =false ) //codigo postal
    private int cd_postal;

    @Column(name="ciudad", nullable =false )
    private String ciudad;

    @Column(name="ct_paquetes", nullable =false ) //cantidad paquetes
    private int ct_paquetes;

    @Column(name="estado", nullable =false ) 
    private String estado;

    @Column(name="fecha_estado1", nullable =false )
    private Date fecha_estado1;

    @Column(name="fecha_estado2", nullable =false )
    private Date fecha_estado2;

    @Column(name="fecha_estado3", nullable =false )
    private Date fecha_estado3;

    @ManyToOne
    @JoinColumn(name="cliente_cedula")
    private cliente cliente;

    @ManyToOne
    @JoinColumn(name="despachador_cedula")
    private despachador despachador;

    @ManyToOne
    @JoinColumn(name="conductor_cedula")
    private conductor conductor;
}