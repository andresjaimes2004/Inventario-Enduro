package com.enduro.inventario.Controller;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> getAllEstados() {
        return ResponseEntity.ok(estadoService.getEstado());
    }

    @PostMapping
    public ResponseEntity<String> saveEstado(@RequestBody Estado estado) {
        estadoService.saveEstado(estado);
        return ResponseEntity.ok("Estado guardado correctamente.");
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editEstado(
            @RequestParam String nombreEstado,
            @RequestParam String nombreNuevo
    ) {
        boolean editado = estadoService.editestado(nombreEstado, nombreNuevo);
        if (editado) {
            return ResponseEntity.ok("Estado actualizado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteEstado(@RequestParam String nombreEstado) {
        boolean eliminado = estadoService.deleteEstado(nombreEstado);
        if (eliminado) {
            return ResponseEntity.ok("Estado eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Estado>> findByNombreEstado(@RequestParam String nombreEstado) {
        return ResponseEntity.ok(estadoService.findByNombreEstado(nombreEstado));
    }
}
