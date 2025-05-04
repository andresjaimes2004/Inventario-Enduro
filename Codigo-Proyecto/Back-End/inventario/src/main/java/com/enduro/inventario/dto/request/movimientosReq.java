package com.enduro.inventario.dto.request;

<<<<<<< HEAD
public class movimientosReq {
    private Integer id_movimiento;
    private Integer id_usuario;
    private Integer id_producto;
    private String razon;
    private String tipo;
    private Integer cantidad;
    private String fecha_movimiento;
}
=======
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class movimientosReq {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer idProducto;

    @NotBlank(message = "La razón del movimiento es obligatoria")
    @Pattern(regexp = "VENTA_DE_PRODUCTO|DEVOLUCION_DE_PRODUCTO|COMPRA_DE_PRODUCTO",
            message = "Razón de movimiento no válida")
    private String razon;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "ENTRADA|SALIDA", message = "Tipo de movimiento no válido")
    private String tipo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El estado es obligatorio")
    private Long idEstado;
}
>>>>>>> rama-secundaria
