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

    @PutMapping("/editar")
    public ResponseEntity<String> editProducto(@RequestBody Producto producto) {

        try {
            productoService.editProducto(producto);
            return ResponseEntity.ok("Producto editado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al editar el producto: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducto(@RequestBody Producto producto) {
        boolean eliminado = productoService.deleteProducto(producto);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente.");
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

}
