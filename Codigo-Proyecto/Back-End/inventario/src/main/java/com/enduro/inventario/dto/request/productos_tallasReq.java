package com.enduro.inventario.dto.request;

<<<<<<< HEAD
public class productos_tallasReq {
    private Integer id_producto;
    private Integer id_talla;
}
=======
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class productos_tallasReq {
    @NotNull(message = "El ID del producto es obligatorio")
    private Integer idProducto;

    @NotNull(message = "El ID de la talla es obligatorio")
    private Integer idTalla;
}
>>>>>>> rama-secundaria
