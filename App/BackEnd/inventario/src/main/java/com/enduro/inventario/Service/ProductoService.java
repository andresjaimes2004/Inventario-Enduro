package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Talla;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Producto> GetProductos();

    public void saveProducto(Producto producto);

    public boolean editProducto(Integer id, String nombreProducto, String imagen, String Talla, Integer cantidad, BigDecimal precio, String estado);

    public boolean deleteProducto(String nombreProducto, String talla);

    public Optional<Producto> findByNombreProductoContaining(String nombreProducto);

    public Optional<Producto> findByNombreProducto (String nombreProducto);

    public Optional<Producto> findByTalla(String talla);

}
