package com.enduro.inventario.dto.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import java.util.Date;

@Data
@Builder
public class movimientosRes {
    @Schema(description = "ID del movimiento", example = "1")
    private Long idMovimiento;

    @Schema(description = "ID del usuario", example = "1")
    private Long idUsuario;

    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String nombreUsuario;

    @Schema(description = "ID del producto", example = "1")
    private Integer idProducto;

    @Schema(description = "Nombre del producto", example = "Zapato deportivo")
    private String nombreProducto;

    @Schema(description = "Razón del movimiento", example = "VENTA_DE_PRODUCTO")
    private String razon;

    @Schema(description = "Tipo de movimiento", example = "SALIDA")
    private String tipo;

    @Schema(description = "Cantidad movida", example = "5")
    private Integer cantidad;

    @Schema(description = "Estado del movimiento", example = "COMPLETADO")
    private String estado;

    @Schema(description = "Fecha del movimiento")
    private Date fechaMovimiento;
}