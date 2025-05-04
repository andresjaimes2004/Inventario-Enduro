package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.request.usuariosReq;
import com.enduro.inventario.dto.reponse.usuariosRes;
<<<<<<< HEAD
//import com.enduro.inventario.entity.estados;
import com.enduro.inventario.entity.usuarios;
// import com.enduro.exception.ResourceNotFoundException;
//import com.enduro.inventario.repo.estadosrepo;
=======
import com.enduro.inventario.entity.estados;
import com.enduro.inventario.entity.usuarios;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.estadosRepository;
>>>>>>> rama-secundaria
import com.enduro.inventario.repo.UsuariosRepository;
import com.enduro.inventario.service.usuariosService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
<<<<<<< HEAD
//import org.springframework.security.crypto.password.PasswordEncoder;
=======
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> rama-secundaria
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> rama-secundaria
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements usuariosService {

<<<<<<< HEAD
    private final UsuariosRepository usuariosRepository ;
    //private final estadosRepository estadoRepository;
    private final ModelMapper modelMapper;
    //private final PasswordEncoder passwordEncoder;
=======
    private final UsuariosRepository usuariosRepository;
    private final estadosRepository estadoRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
>>>>>>> rama-secundaria

    @Override
    @Transactional
    public usuariosRes crearUsuario(usuariosReq usuarioRequest) {
<<<<<<< HEAD
        // Validar que el nombre de usuario no exista
=======
>>>>>>> rama-secundaria
        if (usuariosRepository.existsByNombre(usuarioRequest.getNombre())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        usuarios usuario = modelMapper.map(usuarioRequest, usuarios.class);
<<<<<<< HEAD
        //usuario.setContraseña(passwordEncoder.encode(usuarioRequest.getContraseña()));

        //estados estado = estadosRepository.findById(usuarioRequest.getIdEstado())
                //.orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        //usuarios.setestados(estado);
=======
        usuario.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));

        estados estado = estadoRepository.findById(usuarioRequest.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        usuario.setEstado(estado);
>>>>>>> rama-secundaria

        usuarios usuarioGuardado = usuariosRepository.save(usuario);
        return convertirAResponse(usuarioGuardado);
    }

    @Override
    public usuariosRes obtenerUsuarioPorId(Long id) {
        usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return convertirAResponse(usuario);
    }

    @Override
    public List<usuariosRes> listarTodosUsuarios() {
        return usuariosRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest usuarioRequest) {
        usuarios usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        modelMapper.map(usuarioRequest, usuarioExistente);
        usuarioExistente.setContraseña(passwordEncoder.encode(usuarioRequest.getContraseña()));

        Estado estado = estadoRepository.findById(usuarioRequest.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        usuarioExistente.setEstado(estado);

        Usuario usuarioActualizado = usuariosRepository.save(usuarioExistente);
=======
    public usuariosRes actualizarUsuario(Long id, usuariosReq usuarioRequest) {
        usuarios usuarioExistente = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        modelMapper.map(usuarioRequest, usuarioExistente);
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));

        estados estado = estadoRepository.findById(usuarioRequest.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        usuarioExistente.setEstado(estado);

        usuarios usuarioActualizado = usuariosRepository.save(usuarioExistente);
>>>>>>> rama-secundaria
        return convertirAResponse(usuarioActualizado);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
<<<<<<< HEAD
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    private UsuarioResponse convertirAResponse(usuarios usuario) {
=======
        usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuariosRepository.delete(usuario);
    }

    private usuariosRes convertirAResponse(usuarios usuario) {
>>>>>>> rama-secundaria
        usuariosRes response = modelMapper.map(usuario, usuariosRes.class);
        response.setEstado(usuario.getEstado().getNombreEstado());
        return response;
    }
<<<<<<< HEAD

}
=======
    @Override
    public boolean existePorNombre(String nombre) {
        return usuariosRepository.existsByNombre(nombre);
    }

    @Override
    public List<usuariosRes> buscarPorNombre(String nombre) {
        return usuariosRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }
    public Optional<usuariosRes> buscarPorNombreExacto(String nombre) {
        return usuariosRepository.findByNombre(nombre)
                .map(this::convertirAResponse);
    }
}
>>>>>>> rama-secundaria
