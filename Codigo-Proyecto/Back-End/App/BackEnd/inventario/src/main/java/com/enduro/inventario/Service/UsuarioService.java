package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    public List<Usuario> getUsuario();

    public void saveUsuario(Usuario usuario);

    public boolean deleteUsuario(Usuario usuario);

    public void editUsuario(Usuario usuario);

    public Optional<Usuario> findByNickUsuario(String nickUsuario);

    public Optional<Usuario> findByIdUsuario(Integer id);

    public List<Usuario> findByEstado(String estado);

    public boolean logIn(String NickName, String contrasena);

}
