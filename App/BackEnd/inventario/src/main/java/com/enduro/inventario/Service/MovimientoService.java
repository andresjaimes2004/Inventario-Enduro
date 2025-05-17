package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.TipoMovimiento;
import com.enduro.inventario.Model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    public List<Movimiento> GetMovimientos();

    public void saveMovimiento(String nickUsuario, String nombreProducto, String tallaProducto, String tipoMovimiento, Integer cantidad, String estado);

    public boolean editMovimiento(Usuario usuariomov, Producto producto, TipoMovimiento tipoMovimiento, Integer Cantidadmov, String Estado, LocalDate fechamov);

    public boolean deleteMovimiento(Integer idusuario, LocalDate fechamov);


    public Optional<Movimiento> findByFechaMovimiento(LocalDate fechaMovimiento);


}
