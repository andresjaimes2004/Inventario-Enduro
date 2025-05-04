package com.enduro.inventario.repo;

import com.enduro.inventario.entity.productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface productosRepository extends JpaRepository<productos, Integer> {
    boolean existsByNombreAndUsuario_IdUsuario(String nombre, Long idUsuario);
    List<productos> findByUsuario_IdUsuario(Long idUsuario);
}