package com.enduro.inventario.Model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductosConTallaDTO {
    private String nombreProducto;
    private String imagenProducto;
    private BigDecimal precio;
    private Integer idEstado; // para el Producto
    private List<TallaCantidadDTO> tallas;
}
