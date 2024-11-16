package co.edu.ucentral.grupo2.baselogistica.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(name = "cedula", nullable = false)
    private Long cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="tipo_documento")
    private String tipo_documento;

    @Column(name="correo")
    private String correo;

    @Column(name="contraseña")
    private String contraseña;

    @Column(name="rol", nullable = false)
    private String rol;

    @OneToMany(mappedBy = "cliente")
    private List<pedido> pedidos;
}