package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.request.estadosReq;
import com.enduro.inventario.dto.reponse.estadosRes;
import com.enduro.inventario.entity.estados;
import com.enduro.inventario.exception.BusinessException;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.estadosRepository;
import com.enduro.inventario.service.estadosService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class estadosServiceImpl implements estadosService {

    // Constantes para tipos de entidad
    public static final String TIPO_USUARIO = "USUARIO";
    public static final String TIPO_PRODUCTO = "PRODUCTO";
    public static final String TIPO_MOVIMIENTO = "MOVIMIENTO";

    // Nombres de estados
    public static final String ACTIVO = "Activo";
    public static final String INACTIVO = "Inactivo";
    public static final String DISPONIBLE = "Disponible";
    public static final String DESCONTINUADO = "Descontinuado";
    public static final String PENDIENTE = "Pendiente";
    public static final String COMPLETADO = "Completado";
    public static final String CANCELADO = "Cancelado";

    private final estadosRepository estadoRepository;
    private final ModelMapper modelMapper;

    // Métodos CRUD
    @Override
    @Transactional
    public estadosRes crearEstado(estadosReq estadoRequest) {
        validarTipoEntidad(estadoRequest.getTipoEntidad());

        if (estadoRepository.existsByTipoEntidadAndNombreEstado(
                estadoRequest.getTipoEntidad(),
                estadoRequest.getNombreEstado())) {
            throw new BusinessException("Ya existe un estado con este nombre para el tipo de entidad");
        }

        estados estado = modelMapper.map(estadoRequest, estados.class);
        estados estadoGuardado = estadoRepository.save(estado);
        return modelMapper.map(estadoGuardado, estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoPorId(Long id) {
        estados estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        return modelMapper.map(estado, estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<estadosRes> listarEstadosPorTipo(String tipoEntidad) {
        validarTipoEntidad(tipoEntidad);
        return estadoRepository.findByTipoEntidad(tipoEntidad).stream()
                .map(estado -> modelMapper.map(estado, estadosRes.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<estadosRes> listarTodosEstados() {
        return estadoRepository.findAll().stream()
                .map(estado -> modelMapper.map(estado, estadosRes.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public estadosRes actualizarEstado(Long id, estadosReq estadoRequest) {
        validarTipoEntidad(estadoRequest.getTipoEntidad());

        estados estadoExistente = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        if (estadoRepository.existsByTipoEntidadAndNombreEstadoAndIdNot(
                estadoRequest.getTipoEntidad(),
                estadoRequest.getNombreEstado(),
                id)) {
            throw new BusinessException("Ya existe otro estado con este nombre para el tipo de entidad");
        }

        modelMapper.map(estadoRequest, estadoExistente);
        estados estadoActualizado = estadoRepository.save(estadoExistente);
        return modelMapper.map(estadoActualizado, estadosRes.class);
    }

    @Override
    @Transactional
    public void eliminarEstado(Long id) {
        estados estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        if (estadoEnUso(estado)) {
            throw new BusinessException("No se puede eliminar el estado porque está siendo utilizado");
        }

        estadoRepository.delete(estado);
    }

    // Métodos para obtener estados específicos (entidades)
    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoActivoUsuario() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_USUARIO, ACTIVO)
                .orElseThrow(() -> new BusinessException("Estado 'Activo' para usuarios no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoInactivoUsuario() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_USUARIO, INACTIVO)
                .orElseThrow(() -> new BusinessException("Estado 'Inactivo' para usuarios no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoDisponibleProducto() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_PRODUCTO, DISPONIBLE)
                .orElseThrow(() -> new BusinessException("Estado 'Disponible' para productos no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoDescontinuadoProducto() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_PRODUCTO, DESCONTINUADO)
                .orElseThrow(() -> new BusinessException("Estado 'Descontinuado' para productos no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoPendienteMovimiento() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_MOVIMIENTO, PENDIENTE)
                .orElseThrow(() -> new BusinessException("Estado 'Pendiente' para movimientos no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoCompletadoMovimiento() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_MOVIMIENTO, COMPLETADO)
                .orElseThrow(() -> new BusinessException("Estado 'Completado' para movimientos no configurado"));
    }

    @Override
    @Transactional(readOnly = true)
    public estados obtenerEstadoCanceladoMovimiento() {
        return estadoRepository.findByTipoEntidadAndNombreEstado(TIPO_MOVIMIENTO, CANCELADO)
                .orElseThrow(() -> new BusinessException("Estado 'Cancelado' para movimientos no configurado"));
    }

    // Métodos para obtener estados específicos (DTOs)
    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoActivoUsuarioResponse() {
        return modelMapper.map(obtenerEstadoActivoUsuario(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoInactivoUsuarioResponse() {
        return modelMapper.map(obtenerEstadoInactivoUsuario(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoDisponibleProductoResponse() {
        return modelMapper.map(obtenerEstadoDisponibleProducto(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoDescontinuadoProductoResponse() {
        return modelMapper.map(obtenerEstadoDescontinuadoProducto(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoPendienteMovimientoResponse() {
        return modelMapper.map(obtenerEstadoPendienteMovimiento(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoCompletadoMovimientoResponse() {
        return modelMapper.map(obtenerEstadoCompletadoMovimiento(), estadosRes.class);
    }

    @Override
    @Transactional(readOnly = true)
    public estadosRes obtenerEstadoCanceladoMovimientoResponse() {
        return modelMapper.map(obtenerEstadoCanceladoMovimiento(), estadosRes.class);
    }

    // Inicialización de estados básicos
    @Override
    @Transactional
    public void inicializarEstadosBasicos() {
        crearEstadoSiNoExiste(TIPO_USUARIO, ACTIVO);
        crearEstadoSiNoExiste(TIPO_USUARIO, INACTIVO);
        crearEstadoSiNoExiste(TIPO_PRODUCTO, DISPONIBLE);
        crearEstadoSiNoExiste(TIPO_PRODUCTO, DESCONTINUADO);
        crearEstadoSiNoExiste(TIPO_MOVIMIENTO, PENDIENTE);
        crearEstadoSiNoExiste(TIPO_MOVIMIENTO, COMPLETADO);
        crearEstadoSiNoExiste(TIPO_MOVIMIENTO, CANCELADO);
    }

    // Métodos auxiliares privados
    private void crearEstadoSiNoExiste(String tipo, String nombre) {
        if (!estadoRepository.existsByTipoEntidadAndNombreEstado(tipo, nombre)) {
            estados estado = new estados();
            estado.setTipoEntidad(tipo);
            estado.setNombreEstado(nombre);
            estadoRepository.save(estado);
        }
    }

    private void validarTipoEntidad(String tipoEntidad) {
        if (!List.of(TIPO_USUARIO, TIPO_PRODUCTO, TIPO_MOVIMIENTO).contains(tipoEntidad)) {
            throw new BusinessException("Tipo de entidad no válido. Valores permitidos: USUARIO, PRODUCTO, MOVIMIENTO");
        }
    }

    private boolean estadoEnUso(estados estado) {
        // Implementación real necesaria - verificar en otras entidades
        // Ejemplo: Verificar si hay usuarios, productos o movimientos usando este estado
        return false;
    }
}