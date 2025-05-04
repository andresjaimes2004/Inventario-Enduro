package com.enduro.inventario.dto.request;

<<<<<<< HEAD
import java.math.BigDecimal;

public class productosReq {
    private Integer id_producto;
    private String nombre;
    private Integer stock;
    private String imagen;
    private String fecha_agregado;
    private BigDecimal precio;
    private Integer id_usuario;
}
=======
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class productosReq {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock = 0;

    private String imagen;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;

    private List<Integer> tallas;
}
>>>>>>> rama-secundaria
