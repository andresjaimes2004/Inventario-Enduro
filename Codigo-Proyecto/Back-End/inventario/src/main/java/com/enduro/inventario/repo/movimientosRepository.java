package com.enduro.inventario.repo;

import com.enduro.inventario.entity.movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface movimientosRepository extends JpaRepository<movimientos, Long> {

    // Métodos básicos
    List<movimientos> findByEstadoIdEstado(Long idEstado);
    List<movimientos> findByTipo(String tipo);
    List<movimientos> findByUsuarioIdUsuario(Long idUsuario);
    List<movimientos> findByProductoIdProducto(Integer idProducto);
    List<movimientos> findByRazon(String razon);
    List<movimientos> findByFechaMovimientoBetween(Date fechaInicio, Date fechaFin);

    // Método para búsqueda combinada
    @Query("SELECT m FROM movimientos m WHERE " +
            "(:tipo IS NULL OR m.tipo = :tipo) AND " +
            "(:razon IS NULL OR m.razon = :razon) AND " +
            "(:idEstado IS NULL OR m.estado.idEstado = :idEstado) AND " +
            "(:idUsuario IS NULL OR m.usuario.idUsuario = :idUsuario) AND " +
            "(:idProducto IS NULL OR m.producto.idProducto = :idProducto)")
    List<movimientos> findByTipoAndRazonAndEstadoIdEstadoAndUsuarioIdUsuarioAndProductoIdProducto(
            @Param("tipo") String tipo,
            @Param("razon") String razon,
            @Param("idEstado") Long idEstado,
            @Param("idUsuario") Long idUsuario,
            @Param("idProducto") Integer idProducto);

    // Método para contar movimientos por estado
    @Query("SELECT COUNT(m) FROM movimientos m WHERE m.estado.idEstado = :idEstado")
    Long countByEstado(@Param("idEstado") Long idEstado);

    // Método para obtener movimientos recientes
    @Query("SELECT m FROM movimientos m ORDER BY m.fechaMovimiento DESC LIMIT :limit")
    List<movimientos> findRecentMovimientos(@Param("limit") int limit);
}