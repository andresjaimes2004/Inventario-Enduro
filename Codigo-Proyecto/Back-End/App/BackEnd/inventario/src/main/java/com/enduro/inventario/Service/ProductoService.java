package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.ProductosConTallaDTO;
import com.enduro.inventario.Model.Talla;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Producto> GetProductos();

    public void saveProducto(Producto producto);

    public void editProducto(Producto producto);

    public boolean deleteProducto(Producto producto);

    public Optional<Producto> findByNombreProductoContaining(String nombreProducto);

    public Optional<Producto> findByNombreProducto (String nombreProducto);

    public void saveProductoConTallas(ProductosConTallaDTO DTO);

}
