package com.enduro.inventario.controller;

<<<<<<< HEAD
public class estadosController {
=======
import com.enduro.inventario.dto.request.estadosReq;
import com.enduro.inventario.dto.reponse.estadosRes;
import com.enduro.inventario.service.estadosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class estadosController {
    private estadosService estadoService;

    @PostMapping
    public ResponseEntity<estadosRes> crearEstado(@Valid @RequestBody estadosReq estadoRequest) {
        estadosRes response = estadoService.crearEstado(estadoRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<estadosRes> obtenerEstadoPorId(@PathVariable Long id) {
        estadosRes response = estadoService.obtenerEstadoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipo/{tipoEntidad}")
    public ResponseEntity<List<estadosRes>> listarEstadosPorTipo(
            @PathVariable String tipoEntidad) {
        List<estadosRes> responses = estadoService.listarEstadosPorTipo(tipoEntidad);
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<estadosRes>> listarTodosEstados() {
        List<estadosRes> responses = estadoService.listarTodosEstados();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<estadosRes> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody estadosReq estadoRequest) {
        estadosRes response = estadoService.actualizarEstado(id, estadoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener estados específicos (utilitarios)
    @GetMapping("/usuario/activo")
    public ResponseEntity<estadosRes> obtenerEstadoActivoUsuario() {
        estadosRes response = estadoService.obtenerEstadoActivoUsuarioResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/inactivo")
    public ResponseEntity<estadosRes> obtenerEstadoInactivoUsuario() {
        estadosRes response = estadoService.obtenerEstadoInactivoUsuarioResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/producto/disponible")
    public ResponseEntity<estadosRes> obtenerEstadoDisponibleProducto() {
        estadosRes response = estadoService.obtenerEstadoDisponibleProductoResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/producto/descontinuado")
    public ResponseEntity<estadosRes> obtenerEstadoDescontinuadoProducto() {
        estadosRes response = estadoService.obtenerEstadoDescontinuadoProductoResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movimiento/pendiente")
    public ResponseEntity<estadosRes> obtenerEstadoPendienteMovimiento() {
        estadosRes response = estadoService.obtenerEstadoPendienteMovimientoResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movimiento/completado")
    public ResponseEntity<estadosRes> obtenerEstadoCompletadoMovimiento() {
        estadosRes response = estadoService.obtenerEstadoCompletadoMovimientoResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movimiento/cancelado")
    public ResponseEntity<estadosRes> obtenerEstadoCanceladoMovimiento() {
        estadosRes response = estadoService.obtenerEstadoCanceladoMovimientoResponse();
        return ResponseEntity.ok(response);
    }
>>>>>>> rama-secundaria
}
