package com.enduro.inventario.service;

import com.enduro.inventario.dto.reponse.usuariosRes;
import com.enduro.inventario.dto.request.usuariosReq;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> rama-secundaria

public interface usuariosService {
    usuariosRes crearUsuario(usuariosReq usuarioRequest);
    usuariosRes obtenerUsuarioPorId(Long id);
    List<usuariosRes> listarTodosUsuarios();
    usuariosRes actualizarUsuario(Long id, usuariosReq usuarioRequest);
    void eliminarUsuario(Long id);
<<<<<<< HEAD
    }

=======
    Optional<usuariosRes> buscarPorNombreExacto(String nombre);
    // Métodos adicionales recomendados
    boolean existePorNombre(String nombre);
    List<usuariosRes> buscarPorNombre(String nombre);
}
>>>>>>> rama-secundaria
