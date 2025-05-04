package com.enduro.inventario.repo;

import com.enduro.inventario.entity.estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
@Repository
public interface estadosRepository extends JpaRepository<estados, Long> {
    }
=======

import java.util.List;
import java.util.Optional;
@Repository
public interface estadosRepository extends JpaRepository<estados, Long> {
    boolean existsByTipoEntidadAndNombreEstado(String tipoEntidad, String nombreEstado);
    boolean existsByTipoEntidadAndNombreEstadoAndIdNot(String tipoEntidad, String nombreEstado, Long id);
    List<estados> findByTipoEntidad(String tipoEntidad);
    Optional<estados> findByTipoEntidadAndNombreEstado(String tipoEntidad, String nombreEstado);
}
>>>>>>> rama-secundaria

