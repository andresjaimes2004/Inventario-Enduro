package com.enduro.inventario.Controller;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Usuario;
import com.enduro.inventario.Service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<String> guardarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            movimientoService.saveMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento guardado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al guardar el movimiento: " + e.getMessage());
        }
    }


    @PutMapping("/editar")
    public ResponseEntity<String> editMovimiento(@RequestBody Movimiento movimiento) {
        try {
            movimientoService.saveMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento editado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al editar el movimiento: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteMovimiento(@RequestBody Movimiento movimiento) {

        boolean eliminado = movimientoService.deleteMovimiento(movimiento);
        if (eliminado) {
            return ResponseEntity.ok("Movimiento eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{id}")
    public List<Movimiento> findByUsuario(@PathVariable("id") Integer idUsuario) {
        return movimientoService.findByUduario(idUsuario);
    }

    @GetMapping("/usuario/{id}/fecha")
    public List<Movimiento> findByUsuarioAndFecha(
            @PathVariable("id") Integer idUsuario,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha
    ) {
        return movimientoService.findByUsuarioAndFechaMovimiento(idUsuario, fecha);
    }

    // Buscar movimiento por tipo de movimiento
    @GetMapping("/tipo")
    public List<Movimiento> findByTipoMovimiento(@RequestParam String tipoMovimiento) {
        return movimientoService.findByTipoMovimiento(tipoMovimiento);
    }


}
