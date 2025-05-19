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
    public ResponseEntity<String> editEstado(@RequestBody Estado estado) {
        try{
            estadoService.editestado(estado);
            return ResponseEntity.ok("Estado editado exitosamente.");

        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al editar el estado: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteEstado(@RequestBody Estado estado) {
        try{
            estadoService.deleteEstado(estado);
            return ResponseEntity.ok("Estado eliminado exitosamente.");

        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar el estado: " + e.getMessage());
        }
    }

}
