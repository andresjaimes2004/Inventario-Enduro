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
    public boolean deleteUsuario(Usuario usuario) {
        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Eliminado");
        if(estadoOptional.isPresent()){
            usuario.setEstadoUsuario(estadoOptional.get());
            this.saveUsuario(usuario);
        }
        return false;
    }

    @Override
    public void editUsuario(Usuario usuario) {
        this.saveUsuario(usuario);

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
    public boolean logIn(String NickName, String contrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNickUsuario(NickName);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioProv = usuarioOptional.get();
            return usuarioProv.getContrasenaUsuario().equals(contrasena);
        }
        return false;
    }
}
