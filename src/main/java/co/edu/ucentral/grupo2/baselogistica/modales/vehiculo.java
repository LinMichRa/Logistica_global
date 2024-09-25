package co.edu.ucentral.grupo2.baselogistica.modales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name= "vehiculo")
@Builder

public class vehiculo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="placa")
    private String placa;

    @Column(name="marca")
    private String marca;

    @Column(name="modelo")
    private int modelo;

    @Column(name="cap_peso") //capacidad de peso
    private int cap_peso;
}