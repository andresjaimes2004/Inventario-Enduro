package com.enduro.inventario.controller;

<<<<<<< HEAD

import com.enduro.inventario.dto.request.usuariosReq;
import com.enduro.inventario.dto.reponse.usuariosRes;
import com.enduro.inventario.service.Impl.usuarioServiceImpl;
=======
import com.enduro.inventario.dto.request.usuariosReq;
import com.enduro.inventario.dto.reponse.usuariosRes;
>>>>>>> rama-secundaria
import com.enduro.inventario.service.usuariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class usuariosController {

    private final usuariosService usuarioService;
    private final usuarioServiceImpl usuarioServiceImpl;

    @PostMapping
    public ResponseEntity<usuariosRes> crearUsuario(@Valid @RequestBody usuariosReq usuarioRequest) {
        usuariosRes response = usuarioServiceImpl.crearUsuario(usuarioRequest);
=======
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class usuariosController {

    private final usuariosService usuarioService; // Usa la interfaz, no la implementación

    @PostMapping
    public ResponseEntity<usuariosRes> crearUsuario(@Valid @RequestBody usuariosReq usuarioRequest) {
        usuariosRes response = usuarioService.crearUsuario(usuarioRequest);
>>>>>>> rama-secundaria
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<usuariosRes> obtenerUsuario(@PathVariable Long id) {
        usuariosRes response = usuarioServiceImpl.obtenerUsuarioPorId(id);
=======
    public ResponseEntity<usuariosRes> obtenerUsuarioPorId(@PathVariable Long id) {
        usuariosRes response = usuarioService.obtenerUsuarioPorId(id);
>>>>>>> rama-secundaria
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<usuariosRes>> listarUsuarios() {
<<<<<<< HEAD
        List<usuariosRes> responses = usuarioServiceImpl.listarTodosUsuarios();
=======
        List<usuariosRes> responses = usuarioService.listarTodosUsuarios();
>>>>>>> rama-secundaria
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<usuariosRes> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody usuariosReq usuarioRequest) {
<<<<<<< HEAD
        usuariosRes response = usuarioServiceImpl.actualizarUsuario(id, usuarioRequest);
=======
        usuariosRes response = usuarioService.actualizarUsuario(id, usuarioRequest);
>>>>>>> rama-secundaria
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
<<<<<<< HEAD
        usuarioServiceImpl.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
=======
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para búsqueda
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existePorNombre(@RequestParam String nombre) {
        boolean existe = usuarioService.existePorNombre(nombre);
        return ResponseEntity.ok(existe);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<usuariosRes>> buscarPorNombre(@RequestParam String nombre) {
        List<usuariosRes> responses = usuarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<usuariosRes> obtenerUsuarioPorNombre(@PathVariable String nombre) {
        return usuarioService.buscarPorNombreExacto(nombre)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
>>>>>>> rama-secundaria
}