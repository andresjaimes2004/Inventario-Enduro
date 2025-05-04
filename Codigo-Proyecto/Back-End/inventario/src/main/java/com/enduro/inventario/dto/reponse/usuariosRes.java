package com.enduro.inventario.dto.reponse;

<<<<<<< HEAD
import com.enduro.inventario.entity.usuarios;
import lombok.Data;
=======
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

>>>>>>> rama-secundaria
import java.util.Date;

@Data
public class usuariosRes {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
<<<<<<< HEAD
=======
    private Long idEstado;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
>>>>>>> rama-secundaria
    private Date fechaCreacion;
}
