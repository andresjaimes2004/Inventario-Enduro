package com.enduro.inventario.repo;

import com.enduro.inventario.entity.productos_tallas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface productos_tallasRepository extends JpaRepository<productos_tallas, Long> {
    Optional<productos_tallas> findByProducto_IdProductoAndTalla_IdTalla(Integer idProducto, Integer idTalla);
    List<productos_tallas> findAllByProducto_IdProducto(Integer idProducto);
    boolean existsByProducto_IdProductoAndTalla_IdTalla(Integer idProducto, Integer idTalla);
    void deleteByProducto_IdProductoAndTalla_IdTalla(Integer idProducto, Integer idTalla);
}