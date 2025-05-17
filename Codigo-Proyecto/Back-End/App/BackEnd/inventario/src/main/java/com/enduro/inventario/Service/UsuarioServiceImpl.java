package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Usuario;
import com.enduro.inventario.Repository.EstadoRepository;
import com.enduro.inventario.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Usuario> getUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public void saveUsuario(Usuario usuario) {

        usuarioRepository.save(usuario);

    }

    @Override
    public boolean deleteUsuario(Integer id) {

        Optional<Usuario> usuarioOptional=findByIdUsuario(id);
        Optional<Estado> optionalEstado = estadoRepository.findByNombreEstado("Elminado");

        if (usuarioOptional.isPresent() && optionalEstado.isPresent()){

            Usuario userProv = usuarioOptional.get();
            userProv.setEstadoUsuario(optionalEstado.get());
            usuarioRepository.save(userProv);

            return true;

        }

        return false;
    }

    @Override
    public boolean editUsuario(Integer id, String nombreUsuario, String apellidoUsuario, String nickUsuario, String contraseñaUsuairo, String estado) {

        Optional<Usuario> usuarioOptional = findByIdUsuario(id);
        Optional<Estado> estadoOptional = estadoRepository.findByNombreEstado(estado);

        if (usuarioOptional.isPresent() && estadoOptional.isPresent()){

            Usuario userProv = usuarioOptional.get();

            userProv.setNombreUsuario(nombreUsuario);
            userProv.setApellidoUsuario(apellidoUsuario);
            userProv.setNickUsuario(nickUsuario);
            userProv.setContraseñaUsuario(contraseñaUsuairo);
            userProv.setEstadoUsuario(estadoOptional.get());

            usuarioRepository.save(userProv);

            return true;

        }

        return false;
    }

    @Override
    public Optional<Usuario> findByNickUsuario(String nickUsuario) {
        return usuarioRepository.findByNickUsuario(nickUsuario);
    }

    @Override
    public Optional<Usuario> findByIdUsuario(Integer id) {
        return usuarioRepository.findByIdUsuario(id);
    }

    @Override
    public List<Usuario> findByEstado(String estado) {
        Optional<Estado> estadoOptional= estadoRepository.findByNombreEstado(estado);
        if (estadoOptional.isPresent()) {
            Estado estadoProv = estadoOptional.get();
            return usuarioRepository.findByEstadoUsuario(estadoProv);
        }
        else return null;
    }

    @Override
    public boolean logIn(String NickName, String contraseña) {

        Optional<Usuario> usuarioOptional=usuarioRepository.findByNickUsuario(NickName);
        Usuario usuarioProv=usuarioOptional.get();
        if (usuarioProv.getContraseñaUsuario().equals(contraseña)){
            return true;
        }

        return false;
    }
}
