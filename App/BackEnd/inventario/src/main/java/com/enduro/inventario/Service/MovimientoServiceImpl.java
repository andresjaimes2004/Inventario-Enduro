package com.enduro.inventario.Service;

import com.enduro.inventario.Model.*;
import com.enduro.inventario.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository  usuarioRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    public List<Movimiento> GetMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public void saveMovimiento(String nickUsuario, String nombreProducto, String tallaProducto,
                               String tipoMovimiento, Integer cantidad, String estado) {
        Optional<Usuario> usuarioOptional=usuarioRepository.findByNickUsuario(nickUsuario);
        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado(estado);
        Optional<Talla> tallaOptional = tallaRepository.findByNTalla(tallaProducto);
        Talla tallabusc = tallaOptional.get();
        Optional<Producto> productoOptional=productoRepository.findByNombreProductoAndTalla(nombreProducto,
                tallabusc);

        if (usuarioOptional.isPresent() && estadoOptional.isPresent() && productoOptional.isPresent()) {

            Usuario usuarioProv = usuarioOptional.get();
            Estado estadoProv = estadoOptional.get();
            Producto productoProv = productoOptional.get();

            TipoMovimiento tipo;
            try {
                tipo = TipoMovimiento.valueOf(tipoMovimiento.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de movimiento inválido: " + tipoMovimiento);
            }

            Movimiento movimiento = new Movimiento(usuarioProv, productoProv, tipo, cantidad, estadoProv);

        }else {
            throw new RuntimeException("No se encontró usuario, estado o producto con los datos proporcionados.");
        }
    }

    @Override
    public boolean editMovimiento(Usuario usuariomov, Producto producto, TipoMovimiento tipoMovimiento,
                                  Integer Cantidadmov, String Estado, LocalDate fechamov) {

        Optional<Movimiento> movimientoOptional = findByFechaMovimiento(fechamov);
        Optional<Estado> estadoOptional = estadoRepository.findByNombreEstado(Estado);

        if (movimientoOptional.isPresent() && estadoOptional.isPresent()){
            Movimiento movimientoprov=movimientoOptional.get();
            movimientoprov.setUsuario(usuariomov);
            movimientoprov.setProducto(producto);
            movimientoprov.setTipoMovimiento(tipoMovimiento);
            movimientoprov.setCantidad(Cantidadmov);
            movimientoprov.setEstado(estadoOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMovimiento(Integer idUsuario, LocalDate fechaMovimiento) {


        Optional<Usuario> usuarioOptional = usuarioRepository.findByIdUsuario(idUsuario);
        Usuario usuarioProv=usuarioOptional.get();
        Optional<Movimiento> movimientoOptional = movimientoRepository.findByUsuarioAndFechaMovimiento(usuarioProv,
                fechaMovimiento);
        if (movimientoOptional.isPresent()){
            Movimiento movimientoProv=movimientoOptional.get();
            saveMovimiento(usuarioProv.getNickUsuario(),movimientoProv.getProducto().getNombreProducto(),
                    movimientoProv.getProducto().getTalla().getnTalla(), movimientoProv.getTipoMovimiento().toString(),
                    movimientoProv.getCantidad(),movimientoProv.getEstado().getNombreEstado());
            return true;
        }

        return false;
    }



    @Override
    public Optional<Movimiento> findByFechaMovimiento(LocalDate fechaMovimiento) {
        return movimientoRepository.findByFechaMovimiento(fechaMovimiento);
    }

}
