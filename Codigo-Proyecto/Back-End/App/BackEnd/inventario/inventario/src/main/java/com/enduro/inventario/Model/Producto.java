package com.enduro.inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 40)
    private String nombreProducto;

    @Column(name = "imagen_producto",nullable = false, length = 80)
    private String imagenProducto;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    public Producto(String nombreProducto, String imagenProducto, BigDecimal precio, Estado estado) {
        this.nombreProducto = nombreProducto;
        this.imagenProducto = imagenProducto;
        this.precio = precio;
        this.estado = estado;
    }
}
