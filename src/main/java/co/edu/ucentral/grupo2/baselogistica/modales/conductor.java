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
@Table(name= "vehiculo")
@Builder

public class conductor{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COND_REL")
    @SequenceGenerator(name = "SEQ_COND_REL", sequenceName = "SEQ_COND_REL", allocationSize = 1)
    @Column(name="cedula", nullable =false )
    private int cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="licencia")
    private int licencia;

    @Column(name="zona")
    private String zona;

    
}