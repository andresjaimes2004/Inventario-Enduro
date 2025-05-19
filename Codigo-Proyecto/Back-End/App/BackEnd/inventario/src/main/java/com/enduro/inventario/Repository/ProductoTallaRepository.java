package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.ProductoTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoTallaRepository extends JpaRepository<ProductoTalla, Integer> {

    List<ProductoTalla> findByProductoIdProducto(Integer idProducto);

}
