package com.enduro.inventario.controller;

import com.enduro.inventario.dto.reponse.productos_tallasRes;
import com.enduro.inventario.dto.request.productos_tallasReq;
import com.enduro.inventario.service.productos_tallasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos-tallas")
@RequiredArgsConstructor
public class productos_tallasController {

    private final productos_tallasService productosTallasService;

    @PostMapping
    public ResponseEntity<productos_tallasRes> crearRelacion(
            @Valid @RequestBody productos_tallasReq request) {
        productos_tallasRes response = productosTallasService.crearRelacion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarRelacion(
            @RequestParam Integer idProducto,
            @RequestParam Integer idTalla) {
        productosTallasService.eliminarRelacion(idProducto, idTalla);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-producto/{idProducto}")
    public ResponseEntity<List<productos_tallasRes>> obtenerTallasPorProducto(
            @PathVariable Integer idProducto) {
        List<productos_tallasRes> response = productosTallasService.obtenerTallasPorProducto(idProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-talla/{idTalla}")
    public ResponseEntity<List<productos_tallasRes>> obtenerProductosPorTalla(
            @PathVariable Integer idTalla) {
        List<productos_tallasRes> response = productosTallasService.obtenerProductosPorTalla(idTalla);
        return ResponseEntity.ok(response);
    }
}