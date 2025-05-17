package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    public Optional<Usuario> findByNickUsuarioContaining(String nickUsuario);

    public Optional<Usuario> findByNickUsuario(String nickUsuario);

    public Optional<Usuario> findByIdUsuario(Integer idUsuario);

    public List<Usuario> findByEstadoUsuario(Estado estado);
}
