package com.enduro.inventario.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "PRODUCTOS")
public class productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private usuarios usuario;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Integer stock = 0;

    private String imagen;

    @Column(nullable = false, precision = 9, scale = 2)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private estados estado;

    @Column(name = "fecha_agregado", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAgregado = new Date();

    @ManyToMany
    @JoinTable(
            name = "PRODUCTOS_TALLAS",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_talla")
    )
    private List<tallas> tallas;
}