package co.edu.ucentral.grupo2.baselogistica.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name= "clientes")
@Builder

public class cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIE_REL")
    @SequenceGenerator(name = "SEQ_CLIE_REL", sequenceName = "SEQ_CLIE_REL", allocationSize = 1)
    @Column(name="cedula", nullable =false )
    private Long cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="tipo_documento")
    private String tipo_documento;

    @OneToMany(mappedBy = "cliente")
    private List<pedido> pedidos;
}