package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    public Optional<Estado> findByNombreEstado(String nombreEstado);


}
