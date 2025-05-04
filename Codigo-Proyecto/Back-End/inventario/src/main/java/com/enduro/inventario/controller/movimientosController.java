package com.enduro.inventario.controller;

<<<<<<< HEAD
=======
import com.enduro.inventario.dto.reponse.movimientosRes;
import com.enduro.inventario.dto.request.movimientosReq;
import com.enduro.inventario.service.movimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

>>>>>>> rama-secundaria
@RestController
@RequestMapping("/api/movimientos")
public class movimientosController {

<<<<<<< HEAD
    @Autowired
    private movimientosService movimientosService;

    @GetMapping
    public List<movimientos> listarMovimientos() {
        return movimientosService.getAllMovimientos();
=======
    private final movimientosService movimientosService;

    @Autowired
    public movimientosController(movimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @PostMapping
    public ResponseEntity<movimientosRes> crearMovimiento(@RequestBody movimientosReq request) {
        movimientosRes response = movimientosService.crearMovimiento(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<movimientosRes> obtenerMovimientoPorId(@PathVariable Long id) {
        movimientosRes response = movimientosService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<movimientosRes>> listarPorEstado(@PathVariable Long idEstado) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorEstado(idEstado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<movimientosRes>> listarPorTipo(@PathVariable String tipo) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorTipo(tipo);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<movimientosRes>> listarTodos() {
        List<movimientosRes> response = movimientosService.listarTodosMovimientos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<movimientosRes>> listarPorUsuario(@PathVariable Long idUsuario) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorUsuario(idUsuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<movimientosRes>> listarPorProducto(@PathVariable Integer idProducto) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorProducto(idProducto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/estado/{idEstado}")
    public ResponseEntity<movimientosRes> actualizarEstado(
            @PathVariable Long id,
            @PathVariable Long idEstado) {
        movimientosRes response = movimientosService.actualizarEstadoMovimiento(id, idEstado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/razon/{razon}")
    public ResponseEntity<List<movimientosRes>> listarPorRazon(@PathVariable String razon) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorRazon(razon);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<movimientosRes>> listarPorRangoFechas(
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin) {
        List<movimientosRes> response = movimientosService.listarMovimientosPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<movimientosRes>> filtrarMovimientos(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String razon,
            @RequestParam(required = false) Long idEstado,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Integer idProducto) {
        List<movimientosRes> response = movimientosService.filtrarMovimientos(tipo, razon, idEstado, idUsuario, idProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/contar/estado/{idEstado}")
    public ResponseEntity<Long> contarPorEstado(@PathVariable Long idEstado) {
        Long count = movimientosService.contarMovimientosPorEstado(idEstado);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/recientes")
    public ResponseEntity<List<movimientosRes>> listarRecientes(
            @RequestParam(defaultValue = "5") int limit) {
        List<movimientosRes> response = movimientosService.listarMovimientosRecientes(limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/completados")
    public ResponseEntity<List<movimientosRes>> listarCompletados() {
        List<movimientosRes> response = movimientosService.buscarMovimientosCompletados();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<movimientosRes>> listarPendientes() {
        List<movimientosRes> response = movimientosService.buscarMovimientosPendientes();
        return ResponseEntity.ok(response);
>>>>>>> rama-secundaria
    }
}