package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.TipoMovimiento;
import com.enduro.inventario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    public List<Movimiento> findByUsuario(Usuario usuario);

    public List<Movimiento> findByUsuarioAndFechaMovimiento(Usuario usuario, LocalDateTime fechaMov);

    public List<Movimiento> findByTipoMovimiento(TipoMovimiento tipo_movimiento);

}
