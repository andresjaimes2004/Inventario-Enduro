package com.enduro.inventario.service;

import com.enduro.inventario.dto.reponse.productosRes;
import com.enduro.inventario.dto.request.productosReq;
import java.util.List;

public interface productosService {
    productosRes crearProducto(productosReq request);
    productosRes obtenerProductoPorId(Integer id);
    List<productosRes> listarTodosProductos();
    List<productosRes> listarProductosPorUsuario(Integer idUsuario);
    productosRes actualizarProducto(Integer id, productosReq request);
    void eliminarProducto(Integer id);
    productosRes agregarTallaAProducto(Integer idProducto, Integer idTalla);
    productosRes removerTallaDeProducto(Integer idProducto, Integer idTalla);
}