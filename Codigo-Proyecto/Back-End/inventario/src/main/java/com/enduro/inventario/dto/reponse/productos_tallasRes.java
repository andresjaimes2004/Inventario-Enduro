package com.enduro.inventario.dto.reponse;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class productos_tallasRes {
    private Integer idProducto;
    private Integer idTalla;
    private String nombreProducto;
    private Integer valorTalla;
}