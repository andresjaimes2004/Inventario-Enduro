package com.enduro.inventario.controller;

import com.enduro.inventario.dto.reponse.productosRes;
import com.enduro.inventario.dto.request.productosReq;
import com.enduro.inventario.service.productosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class productosController {

    private final productosService productosService;

    @PostMapping
    public ResponseEntity<productosRes> crearProducto(@Valid @RequestBody productosReq request) {
        productosRes response = productosService.crearProducto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productosRes> obtenerProductoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(productosService.obtenerProductoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<productosRes>> listarTodosProductos() {
        return ResponseEntity.ok(productosService.listarTodosProductos());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<productosRes>> listarProductosPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(productosService.listarProductosPorUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<productosRes> actualizarProducto(
            @PathVariable Integer id,
            @Valid @RequestBody productosReq request) {
        return ResponseEntity.ok(productosService.actualizarProducto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idProducto}/tallas/{idTalla}")
    public ResponseEntity<productosRes> agregarTalla(
            @PathVariable Integer idProducto,
            @PathVariable Integer idTalla) {
        return ResponseEntity.ok(productosService.agregarTallaAProducto(idProducto, idTalla));
    }

    @DeleteMapping("/{idProducto}/tallas/{idTalla}")
    public ResponseEntity<productosRes> removerTalla(
            @PathVariable Integer idProducto,
            @PathVariable Integer idTalla) {
        return ResponseEntity.ok(productosService.removerTallaDeProducto(idProducto, idTalla));
    }
}