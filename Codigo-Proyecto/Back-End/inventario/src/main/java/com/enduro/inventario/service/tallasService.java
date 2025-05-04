package com.enduro.inventario.service;

import com.enduro.inventario.dto.reponse.tallasRes;
import com.enduro.inventario.dto.request.tallasReq;
import java.util.List;

public interface tallasService {
    tallasRes crearTalla(tallasReq tallaReq);
    tallasRes obtenerTallaPorId(Integer id);
    List<tallasRes> listarTodasTallas();
    tallasRes actualizarTalla(Integer id, tallasReq tallaReq);
    void eliminarTalla(Integer id);
    List<tallasRes> buscarTallasEnRango(Integer min, Integer max);
    tallasRes buscarPorTallaExacta(Integer talla);
    boolean existeTalla(Integer talla);
}