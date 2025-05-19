package com.enduro.inventario.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TallaCantidadDTO {
    private String numeroTalla;
    private Integer cantidad;
    private Integer idEstado; // ID del estado que se asociará a ProductoTalla
}
