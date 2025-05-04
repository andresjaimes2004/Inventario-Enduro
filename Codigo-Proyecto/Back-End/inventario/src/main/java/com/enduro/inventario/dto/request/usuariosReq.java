package com.enduro.inventario.dto.request;

<<<<<<< HEAD
import com.enduro.inventario.entity.usuarios;
import jakarta.validation.contrains.*;
=======
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
>>>>>>> rama-secundaria
import lombok.Data;

@Data
public class usuariosReq {
    @NotBlank(message = "Nombre es obligatorio")
    @Size(max = 100, message = "Nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    @Size(max = 100, message = "Apellido no puede exceder 100 caracteres")
    private String apellido;

<<<<<<< HEAD
    @NotBlank(message = "Contraseña es obligatoria")
    @Size(min = 6, max = 150, message = "Contraseña debe tener entre 6 y 150 caracteres")
    private String contraseña;
=======
    @NotBlank(message = "Password es obligatorio")
    @Size(min = 6, max = 150, message = "Password debe tener entre 6 y 150 caracteres")
    private String password;  // Cambiado de "contraseña" a "password"
>>>>>>> rama-secundaria

    @NotNull(message = "Rol es obligatorio")
    private String rol;

    @NotNull(message = "Estado es obligatorio")
    private Long idEstado;
}

