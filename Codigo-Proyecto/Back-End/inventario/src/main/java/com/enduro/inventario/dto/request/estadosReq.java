package com.enduro.inventario.dto.request;

<<<<<<< HEAD
public class estadosReq {

=======
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class estadosReq {
    @NotBlank(message = "Tipo de entidad es obligatorio")
    private String tipoEntidad;

    @NotBlank(message = "Nombre de estado es obligatorio")
    private String nombreEstado;

    private String descripcion;
>>>>>>> rama-secundaria
}
