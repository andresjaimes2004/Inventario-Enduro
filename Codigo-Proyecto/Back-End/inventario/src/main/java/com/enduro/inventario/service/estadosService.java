package com.enduro.inventario.service;

import com.enduro.inventario.dto.request.estadosReq;
import com.enduro.inventario.dto.reponse.estadosRes;
import com.enduro.inventario.entity.estados;
import java.util.List;

public interface estadosService {
    estadosRes crearEstado(estadosReq estadoRequest);
    estadosRes obtenerEstadoPorId(Long id);
    List<estadosRes> listarEstadosPorTipo(String tipoEntidad);
    List<estadosRes> listarTodosEstados();
    estadosRes actualizarEstado(Long id, estadosReq estadoRequest);
    void eliminarEstado(Long id);

    estados obtenerEstadoActivoUsuario();
    estados obtenerEstadoInactivoUsuario();
    estados obtenerEstadoDisponibleProducto();
    estados obtenerEstadoDescontinuadoProducto();
    estados obtenerEstadoPendienteMovimiento();
    estados obtenerEstadoCompletadoMovimiento();
    estados obtenerEstadoCanceladoMovimiento();

    estadosRes obtenerEstadoActivoUsuarioResponse();
    estadosRes obtenerEstadoInactivoUsuarioResponse();
    estadosRes obtenerEstadoDisponibleProductoResponse();
    estadosRes obtenerEstadoDescontinuadoProductoResponse();
    estadosRes obtenerEstadoPendienteMovimientoResponse();
    estadosRes obtenerEstadoCompletadoMovimientoResponse();
    estadosRes obtenerEstadoCanceladoMovimientoResponse();

    void inicializarEstadosBasicos();
}