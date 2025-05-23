package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Movimiento;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Usuario;
import com.enduro.inventario.Model.TipoMovimiento;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    //trae todos los movimientos que estan la base de datos
    public List<Movimiento> GetMovimientos();

    //crea un nuevo movimiento con el estado activo por default
    public void saveMovimiento(Movimiento movimiento);

    //el metodo de editar solo permite editar el producto y su cantidad y el tipo de movimiento
    public void editMovimiento(Movimiento movimiento);

    public boolean deleteMovimiento(Movimiento movimiento);

    //Para buscar segun el usuario que hizo el movimiento, usando su id
    public List<Movimiento> findByUduario(Integer id_usuario);

    public List<Movimiento> findByUsuarioAndFechaMovimiento(Integer id_usuario, LocalDateTime fechaMov);

    public List<Movimiento> findByTipoMovimiento(String tipo_movimiento);

}
