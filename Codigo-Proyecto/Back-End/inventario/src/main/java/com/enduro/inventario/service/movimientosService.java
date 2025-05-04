package com.enduro.inventario.service;

import com.enduro.inventario.dto.reponse.movimientosRes;
import com.enduro.inventario.dto.request.movimientosReq;
import java.util.Date;
import java.util.List;

public interface movimientosService {
    movimientosRes crearMovimiento(movimientosReq request);
    movimientosRes obtenerMovimientoPorId(Long id);
    List<movimientosRes> listarMovimientosPorEstado(Long idEstado);
    List<movimientosRes> listarMovimientosPorTipo(String tipo);
    List<movimientosRes> listarTodosMovimientos();
    List<movimientosRes> listarMovimientosPorUsuario(Long idUsuario);
    List<movimientosRes> listarMovimientosPorProducto(Integer idProducto);
    movimientosRes actualizarEstadoMovimiento(Long id, Long idEstado);
    List<movimientosRes> listarMovimientosPorRazon(String razon);
    List<movimientosRes> listarMovimientosPorRangoFechas(Date fechaInicio, Date fechaFin);
    List<movimientosRes> filtrarMovimientos(String tipo, String razon, Long idEstado, Long idUsuario, Integer idProducto);
    Long contarMovimientosPorEstado(Long idEstado);
    List<movimientosRes> listarMovimientosRecientes(int limit);
    List<movimientosRes> buscarMovimientosCompletados();
    List<movimientosRes> buscarMovimientosPendientes();
}