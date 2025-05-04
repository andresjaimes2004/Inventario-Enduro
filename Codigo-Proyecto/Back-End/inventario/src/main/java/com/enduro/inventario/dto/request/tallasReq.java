package com.enduro.inventario.dto.request;

<<<<<<< HEAD
public class tallasReq {
    private Integer id_talla;
    private Integer talla;
}
=======
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class tallasReq {

    @NotNull(message = "El valor de talla no puede ser nulo")
    @Min(value = 25, message = "La talla mínima permitida es 25")
    @Max(value = 43, message = "La talla máxima permitida es 43")
    private Integer talla;
}
>>>>>>> rama-secundaria
