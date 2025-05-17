package com.enduro.inventario.Controller;

import com.enduro.inventario.Model.Usuario;
import com.enduro.inventario.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuario());
    }

    @PostMapping
    public ResponseEntity<String> saveUsuario(@RequestBody Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok("Usuario guardado exitosamente.");
    }

    @PostMapping("/Login")
    public boolean Login(@RequestParam String Nickname,@RequestParam String contraseña){

        return usuarioService.logIn(Nickname,contraseña);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        if (deleted) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUsuario(
            @PathVariable Integer id,
            @RequestParam String nombreUsuario,
            @RequestParam String apellidoUsuario,
            @RequestParam String nickUsuario,
            @RequestParam String contraseñaUsuairo,
            @RequestParam String estado) {

        boolean updated = usuarioService.editUsuario(id, nombreUsuario, apellidoUsuario,
                nickUsuario, contraseñaUsuairo, estado);

        if (updated) {
            return ResponseEntity.ok("Usuario actualizado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar-por-nick")
    public ResponseEntity<Optional<Usuario>> findByNickUsuario(@RequestParam String nickUsuario) {
        return ResponseEntity.ok(usuarioService.findByNickUsuario(nickUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findByIdUsuario(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Usuario>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(usuarioService.findByEstado(estado));
    }
}
