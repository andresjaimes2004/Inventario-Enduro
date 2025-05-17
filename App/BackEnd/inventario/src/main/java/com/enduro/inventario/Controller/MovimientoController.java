package com.enduro.inventario.Controller;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.TipoMovimiento;
import com.enduro.inventario.Model.Usuario;
import com.enduro.inventario.Service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.GetMovimientos());
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarMovimiento(
            @RequestParam String nickUsuario,
            @RequestParam String nombreProducto,
            @RequestParam String tallaProducto,
            @RequestParam String tipoMovimiento,
            @RequestParam Integer cantidad,
            @RequestParam String estado) {
        try {
            movimientoService.saveMovimiento(nickUsuario, nombreProducto, tallaProducto, tipoMovimiento, cantidad, estado);
            return ResponseEntity.ok("Movimiento guardado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al guardar el movimiento: " + e.getMessage());
        }
    }


    @PutMapping("/editar")
    public ResponseEntity<String> editMovimiento(
            @RequestParam Integer idUsuario,
            @RequestParam Integer idProducto,
            @RequestParam TipoMovimiento tipoMovimiento,
            @RequestParam Integer cantidad,
            @RequestParam String estado,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaMovimiento
    ) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);

        boolean actualizado = movimientoService.editMovimiento(usuario, producto, tipoMovimiento, cantidad, estado, fechaMovimiento);
        if (actualizado) {
            return ResponseEntity.ok("Movimiento actualizado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteMovimiento(
            @RequestParam Integer idUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaMovimiento
    ) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        boolean eliminado = movimientoService.deleteMovimiento(usuario.getIdUsuario(), fechaMovimiento);
        if (eliminado) {
            return ResponseEntity.ok("Movimiento eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fecha")
    public ResponseEntity<Optional<Movimiento>> findByFechaMovimiento(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaMovimiento
    ) {
        return ResponseEntity.ok(movimientoService.findByFechaMovimiento(fechaMovimiento));
    }
}
