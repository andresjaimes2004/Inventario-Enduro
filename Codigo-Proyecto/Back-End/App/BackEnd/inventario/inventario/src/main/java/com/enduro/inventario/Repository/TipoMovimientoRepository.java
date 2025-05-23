package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

    public Optional<TipoMovimiento> findByDescripcion(String descripcion);

}
