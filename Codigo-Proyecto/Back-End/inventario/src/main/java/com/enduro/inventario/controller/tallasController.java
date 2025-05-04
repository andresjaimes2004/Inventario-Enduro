package com.enduro.inventario.controller;

import com.enduro.inventario.dto.reponse.tallasRes;
import com.enduro.inventario.dto.request.tallasReq;
import com.enduro.inventario.service.tallasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tallas")
@RequiredArgsConstructor
public class tallasController {

    private final tallasService tallaService;

    @PostMapping
    public ResponseEntity<tallasRes> crearTalla(@Valid @RequestBody tallasReq request) {
        tallasRes response = tallaService.crearTalla(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<tallasRes> obtenerTallaPorId(@PathVariable Integer id) {
        tallasRes response = tallaService.obtenerTallaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<tallasRes>> listarTodasTallas() {
        List<tallasRes> responses = tallaService.listarTodasTallas();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<tallasRes> actualizarTalla(
            @PathVariable Integer id,
            @Valid @RequestBody tallasReq request) {
        tallasRes response = tallaService.actualizarTalla(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTalla(@PathVariable Integer id) {
        tallaService.eliminarTalla(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rango")
    public ResponseEntity<List<tallasRes>> buscarTallasEnRango(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        List<tallasRes> responses = tallaService.buscarTallasEnRango(min, max);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/buscar")
    public ResponseEntity<tallasRes> buscarPorTallaExacta(@RequestParam Integer talla) {
        tallasRes response = tallaService.buscarPorTallaExacta(talla);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeTalla(@RequestParam Integer talla) {
        boolean existe = tallaService.existeTalla(talla);
        return ResponseEntity.ok(existe);
    }
}