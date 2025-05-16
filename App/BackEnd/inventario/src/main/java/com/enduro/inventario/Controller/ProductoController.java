package com.enduro.inventario.Controller;

import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.GetProductos());
    }

    @PostMapping
    public ResponseEntity<String> saveProducto(@RequestBody Producto producto) {
        productoService.saveProducto(producto);
        return ResponseEntity.ok("Producto guardado con éxito.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editProducto(
            @PathVariable Integer id,
            @RequestParam String nombreProducto,
            @RequestParam String imagen,
            @RequestParam String talla,
            @RequestParam Integer cantidad,
            @RequestParam BigDecimal precio,
            @RequestParam String estado) {

        boolean updated = productoService.editProducto(id, nombreProducto, imagen, talla, cantidad, precio, estado);

        if (updated) {
            return ResponseEntity.ok("Producto actualizado con éxito.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducto(
            @RequestParam String nombreProducto,
            @RequestParam String talla) {

        boolean deleted = productoService.deleteProducto(nombreProducto, talla);

        if (deleted) {
            return ResponseEntity.ok("Producto eliminado.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Producto>> findByNombreProducto(@RequestParam String nombreProducto) {
        return ResponseEntity.ok(productoService.findByNombreProducto(nombreProducto));
    }

    @GetMapping("/buscar-contiene")
    public ResponseEntity<Optional<Producto>> findByNombreProductoContaining(@RequestParam String nombreProducto) {
        return ResponseEntity.ok(productoService.findByNombreProductoContaining(nombreProducto));
    }

    @GetMapping("/talla/{talla}")
    public ResponseEntity<Optional<Producto>> findByTalla(@PathVariable String talla) {
        return ResponseEntity.ok(productoService.findByTalla(talla));
    }
}
