package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.reponse.movimientosRes;
import com.enduro.inventario.dto.request.movimientosReq;
import com.enduro.inventario.entity.*;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.*;
import com.enduro.inventario.service.movimientosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class movimientosServiceImpl implements movimientosService {

    private final movimientosRepository movimientosRepo;
    private final UsuariosRepository usuariosRepo;
    private final productosRepository productosRepo;
    private final estadosRepository estadosRepo;

    @Override
    public movimientosRes crearMovimiento(movimientosReq request) {
        usuarios usuario = usuariosRepo.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        productos producto = productosRepo.findById(request.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        estados estado = estadosRepo.findById(request.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no válido para movimiento"));

        validarTipoYRazon(request.getTipo(), request.getRazon());

        movimientos movimiento = new movimientos();
        movimiento.setUsuario(usuario);
        movimiento.setProducto(producto);
        movimiento.setRazon(request.getRazon());
        movimiento.setTipo(request.getTipo());
        movimiento.setCantidad(request.getCantidad());
        movimiento.setEstado(estado);
        movimiento.setFechaMovimiento(new Date());

        movimientos movimientoGuardado = movimientosRepo.save(movimiento);

        if (estado.getNombreEstado().equalsIgnoreCase("Completado")) {
            actualizarStockProducto(producto, request.getTipo(), request.getCantidad());
        }

        return convertirAResponse(movimientoGuardado);
    }

    @Override
    public movimientosRes obtenerMovimientoPorId(Long id) {
        movimientos movimiento = movimientosRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));
        return convertirAResponse(movimiento);
    }

    @Override
    public List<movimientosRes> listarMovimientosPorEstado(Long idEstado) {
        return movimientosRepo.findByEstadoIdEstado(idEstado).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> listarMovimientosPorTipo(String tipo) {
        validarTipo(tipo);
        return movimientosRepo.findByTipo(tipo).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> listarTodosMovimientos() {
        return movimientosRepo.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> listarMovimientosPorUsuario(Long idUsuario) {
        return movimientosRepo.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> listarMovimientosPorProducto(Integer idProducto) {
        return movimientosRepo.findByProductoIdProducto(idProducto).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public movimientosRes actualizarEstadoMovimiento(Long id, Long idEstado) {
        movimientos movimiento = movimientosRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));

        estados nuevoEstado = estadosRepo.findById(idEstado)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no válido"));

        if (nuevoEstado.getNombreEstado().equalsIgnoreCase("Completado") &&
                !movimiento.getEstado().getNombreEstado().equalsIgnoreCase("Completado")) {
            actualizarStockProducto(movimiento.getProducto(), movimiento.getTipo(), movimiento.getCantidad());
        }

        movimiento.setEstado(nuevoEstado);
        movimientos movimientoActualizado = movimientosRepo.save(movimiento);

        return convertirAResponse(movimientoActualizado);
    }

    @Override
    public List<movimientosRes> listarMovimientosPorRazon(String razon) {
        validarRazon(razon);
        return movimientosRepo.findByRazon(razon).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> listarMovimientosPorRangoFechas(Date fechaInicio, Date fechaFin) {
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
        return movimientosRepo.findByFechaMovimientoBetween(fechaInicio, fechaFin).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> filtrarMovimientos(String tipo, String razon, Long idEstado, Long idUsuario, Integer idProducto) {
        if (tipo != null) validarTipo(tipo);
        if (razon != null) validarRazon(razon);

        return movimientosRepo.findByTipoAndRazonAndEstadoIdEstadoAndUsuarioIdUsuarioAndProductoIdProducto(
                        tipo, razon, idEstado, idUsuario, idProducto).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Long contarMovimientosPorEstado(Long idEstado) {
        return movimientosRepo.countByEstado(idEstado);
    }

    @Override
    public List<movimientosRes> listarMovimientosRecientes(int limit) {
        return movimientosRepo.findRecentMovimientos(limit).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> buscarMovimientosCompletados() {
        return movimientosRepo.findAll().stream()
                .filter(m -> m.getEstado().getNombreEstado().equalsIgnoreCase("Completado"))
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<movimientosRes> buscarMovimientosPendientes() {
        return movimientosRepo.findAll().stream()
                .filter(m -> m.getEstado().getNombreEstado().equalsIgnoreCase("Pendiente"))
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    private void actualizarStockProducto(productos producto, String tipo, Integer cantidad) {
        if (tipo.equalsIgnoreCase("ENTRADA")) {
            producto.setStock(producto.getStock() + cantidad);
        } else {
            producto.setStock(Math.max(producto.getStock() - cantidad, 0));
        }
        productosRepo.save(producto);
    }

    private movimientosRes convertirAResponse(movimientos movimiento) {
        return movimientosRes.builder()
                .idMovimiento(movimiento.getIdMovimiento())
                .idUsuario(movimiento.getUsuario().getIdUsuario())
                .nombreUsuario(movimiento.getUsuario().getNombre() + " " + movimiento.getUsuario().getApellido())
                .idProducto(movimiento.getProducto().getIdProducto())
                .nombreProducto(movimiento.getProducto().getNombre())
                .razon(movimiento.getRazon())
                .tipo(movimiento.getTipo())
                .cantidad(movimiento.getCantidad())
                .estado(movimiento.getEstado().getNombreEstado())
                .fechaMovimiento(movimiento.getFechaMovimiento())
                .build();
    }

    private void validarTipoYRazon(String tipo, String razon) {
        validarTipo(tipo);
        validarRazon(razon);
    }

    private void validarTipo(String tipo) {
        if (!tipo.matches("ENTRADA|SALIDA")) {
            throw new IllegalArgumentException("Tipo de movimiento no válido");
        }
    }

    private void validarRazon(String razon) {
        if (!razon.matches("VENTA_DE_PRODUCTO|DEVOLUCION_DE_PRODUCTO|COMPRA_DE_PRODUCTO")) {
            throw new IllegalArgumentException("Razón de movimiento no válida");
        }
    }
}