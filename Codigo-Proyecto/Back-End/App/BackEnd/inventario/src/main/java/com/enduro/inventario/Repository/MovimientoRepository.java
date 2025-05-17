package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    public Optional<Movimiento> findByFechaMovimiento(LocalDate fechaMovimiento);

    public Optional<Movimiento> findByUsuarioAndFechaMovimiento(Usuario usuario, LocalDate fechaMov);

}
