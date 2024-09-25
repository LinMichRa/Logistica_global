package co.edu.ucentral.grupo2.baselogistica.modales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "despachador")
@Builder

public class despachador{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESPA_REL")
    @SequenceGenerator(name = "SEQ_DESPA_REL", sequenceName = "SEQ_DESPA_REL", allocationSize = 1)
    @Column(name="cedula", nullable =false )
    private Long cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="direccionBodega") //1 direccion default
    private String direccionBodega;
}