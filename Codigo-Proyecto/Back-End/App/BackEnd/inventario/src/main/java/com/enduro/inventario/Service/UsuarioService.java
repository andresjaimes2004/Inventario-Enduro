package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    public List<Usuario> getUsuario();

    public void saveUsuario(Usuario usuario);

    public boolean deleteUsuario(Integer id);

    public boolean editUsuario(Integer id, String nombreUsuario, String apellidoUsuario,
        String nickUsuario,String contraseñaUsuairo, String estado);

    public Optional<Usuario> findByNickUsuario(String nickUsuario);

    public Optional<Usuario> findByIdUsuario(Integer id);

    public List<Usuario> findByEstado(String estado);

    public boolean logIn(String NickName, String contraseña);

}
