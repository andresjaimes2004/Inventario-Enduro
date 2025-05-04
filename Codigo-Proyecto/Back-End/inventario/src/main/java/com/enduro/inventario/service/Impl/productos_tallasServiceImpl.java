package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.reponse.productos_tallasRes;
import com.enduro.inventario.dto.request.productos_tallasReq;
import com.enduro.inventario.entity.productos;
import com.enduro.inventario.entity.tallas;
import com.enduro.inventario.entity.productos_tallas;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.productosRepository;
import com.enduro.inventario.repo.tallasRepository;
import com.enduro.inventario.repo.productos_tallasRepository;
import com.enduro.inventario.service.productos_tallasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class productos_tallasServiceImpl implements productos_tallasService {

    private final productos_tallasRepository productosTallasRepo;
    private final productosRepository productosRepo;
    private final tallasRepository tallasRepo;

    @Override
    @Transactional
    public productos_tallasRes crearRelacion(productos_tallasReq request) {
        productos producto = productosRepo.findById(request.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        tallas talla = tallasRepo.findById(request.getIdTalla())
                .orElseThrow(() -> new ResourceNotFoundException("Talla no encontrada"));

        if (productosTallasRepo.existsByProductoIdProductoAndTallaIdTalla(
                request.getIdProducto(), request.getIdTalla())) {
            throw new IllegalArgumentException("Esta relación ya existe");
        }

        productos_tallas relacion = new productos_tallas();
        relacion.setProducto(producto);
        relacion.setTalla(talla);

        productos_tallas relacionGuardada = productosTallasRepo.save(relacion);

        return convertirAResponse(relacionGuardada);
    }

    @Override
    @Transactional
    public void eliminarRelacion(Integer idProducto, Integer idTalla) {
        if (!productosTallasRepo.existsByProductoIdProductoAndTallaIdTalla(idProducto, idTalla)) {
            throw new ResourceNotFoundException("Relación no encontrada");
        }
        productosTallasRepo.deleteByProductoIdProductoAndTallaIdTalla(idProducto, idTalla);
    }

    @Override
    public List<productos_tallasRes> obtenerTallasPorProducto(Integer idProducto) {
        return productosTallasRepo.findByProducto_IdProducto(idProducto).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<productos_tallasRes> obtenerProductosPorTalla(Integer idTalla) {
        return productosTallasRepo.findByTalla_IdTalla(idTalla).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    private productos_tallasRes convertirAResponse(productos_tallas relacion) {
        return productos_tallasRes.builder()
                .idProducto(relacion.getProducto().getIdProducto())
                .idTalla(relacion.getTalla().getIdTalla())
                .nombreProducto(relacion.getProducto().getNombre())
                .valorTalla(relacion.getTalla().getTalla())
                .build();
    }
}