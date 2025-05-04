package com.enduro.inventario.service;

import com.enduro.inventario.dto.reponse.productos_tallasRes;
import com.enduro.inventario.dto.request.productos_tallasReq;
import java.util.List;

public interface productos_tallasService {
    productos_tallasRes crearRelacion(productos_tallasReq request);
    void eliminarRelacion(Integer idProducto, Integer idTalla);
    List<productos_tallasRes> obtenerTallasPorProducto(Integer idProducto);
    List<productos_tallasRes> obtenerProductosPorTalla(Integer idTalla);
}