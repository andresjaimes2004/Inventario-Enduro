package com.enduro.inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "producto_talla")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoTalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto_talla", nullable = false)
    private Integer idProductotalla;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = false)
    private Talla talla;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
}
