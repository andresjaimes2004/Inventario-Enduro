package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Talla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public Optional<Producto> findByNombreProductoContaining(String nombreProducto);

    public Optional<Producto> findByNombreProducto (String nombreProducto);

    public Optional<Producto> findByPrecio(BigDecimal precio);

    public  Optional<Producto> findByEstado(Estado Estado);

}
