package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.ProductoTalla;
import com.enduro.inventario.Model.Talla;

import java.util.List;
import java.util.Optional;

public interface ProductoTallaService {

    public List<ProductoTalla> GetProductoTalla();

    public void saveProductoTalla(ProductoTalla productoTalla);

    public void editProductoTalla(ProductoTalla productoTalla);

    public boolean deleteProductoTalla(ProductoTalla productoTalla);


    public List<ProductoTalla> findByProducto(String nameProducto);

}
