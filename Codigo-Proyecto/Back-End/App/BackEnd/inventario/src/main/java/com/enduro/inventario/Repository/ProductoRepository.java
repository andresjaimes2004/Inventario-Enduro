package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Talla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public Optional<Producto> findByNombreProductoContaining(String nombreProducto);

    public Optional<Producto> findByNombreProducto (String nombreProducto);

    public Optional<Producto> findByNombreProductoAndTalla(String nombreProducto, Talla talla);


    public Optional<Producto> findByIdProductoAndTalla(Integer idProducto, Talla talla);

    public Optional<Producto> findByTalla(Talla talla);

}
