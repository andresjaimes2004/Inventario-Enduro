package com.enduro.inventario.Service;

import com.enduro.inventario.Model.*;
import com.enduro.inventario.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private TipoMovimientoRepository tipoMovimientoRepository;


    @Override
    public List<Movimiento> GetMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public void saveMovimiento(Movimiento movimiento) {

        movimientoRepository.save(movimiento);

    }

    @Override
    public void editMovimiento(Movimiento movimiento) {
        this.saveMovimiento(movimiento);
    }

    @Override
    public boolean deleteMovimiento(Movimiento movimiento) {

        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Eliminado");

        if(estadoOptional.isPresent()) {
            movimiento.setEstado(estadoOptional.get());
            this.saveMovimiento(movimiento);
            return true;
        }

        return false;

    }

    @Override
    public List<Movimiento> findByUduario(Integer id_usuario) {

        //busca el usuario por si id, sino existe devuelve una exepcion
        Optional<Usuario> usuarioOptional = usuarioRepository.findByIdUsuario(id_usuario);
        Usuario usuario = usuarioOptional.get();

        return movimientoRepository.findByUsuario(usuario);
    }

    @Override
    public List<Movimiento> findByUsuarioAndFechaMovimiento(Integer id_usuario, LocalDateTime fechaMov) {

        Optional<Usuario> usuarioOptional= usuarioRepository.findByIdUsuario(id_usuario);
        Usuario usuario = usuarioOptional.get();

        return movimientoRepository.findByUsuarioAndFechaMovimiento(usuario, fechaMov);
    }

    @Override
    public List<Movimiento> findByTipoMovimiento(String tipoMovimiento) {

        //busca el tipo de movimiento segun la descripcion y sino esta devuelve execpcion
        Optional<TipoMovimiento> tipoMovimientoOptional=tipoMovimientoRepository.findByDescripcion(tipoMovimiento);

        TipoMovimiento tipo = tipoMovimientoOptional.get();

        return movimientoRepository.findByTipoMovimiento(tipo);
    }
}
