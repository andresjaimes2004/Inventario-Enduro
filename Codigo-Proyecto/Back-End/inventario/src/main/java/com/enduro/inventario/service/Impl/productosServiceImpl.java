package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.reponse.productosRes;
import com.enduro.inventario.dto.reponse.tallasRes;
import com.enduro.inventario.dto.request.productosReq;
import com.enduro.inventario.entity.*;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.*;
import com.enduro.inventario.service.productosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class productosServiceImpl implements productosService {

    private final productosRepository productosRepo;
    private final UsuariosRepository usuariosRepo;
    private final estadosRepository estadosRepo;
    private final tallasRepository tallasRepo;
    private final productos_tallasRepository productosTallasRepo;

    @Override
    @Transactional
    public productosRes crearProducto(productosReq request) {
        usuarios usuario = usuariosRepo.findById(request.getIdUsuario().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        estados estado = estadosRepo.findById(request.getIdEstado().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        if (productosRepo.existsByNombreAndUsuario_IdUsuario(request.getNombre(), usuario.getIdUsuario())) {
            throw new IllegalArgumentException("Ya existe un producto con este nombre para este usuario");
        }

        productos nuevoProducto = new productos();
        nuevoProducto.setUsuario(usuario);
        nuevoProducto.setNombre(request.getNombre());
        nuevoProducto.setStock(request.getStock());
        nuevoProducto.setImagen(request.getImagen());
        nuevoProducto.setPrecio(request.getPrecio());
        nuevoProducto.setEstado(estado);
        nuevoProducto.setFechaAgregado(new Date());

        productos productoGuardado = productosRepo.save(nuevoProducto);

        if (request.getTallas() != null && !request.getTallas().isEmpty()) {
            asignarTallasAProducto(productoGuardado, request.getTallas());
        }

        return convertirAResponse(productoGuardado);
    }

    @Override
    public productosRes obtenerProductoPorId(Integer id) {
        productos producto = productosRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return convertirAResponse(producto);
    }

    @Override
    public List<productosRes> listarTodosProductos() {
        return productosRepo.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<productosRes> listarProductosPorUsuario(Integer idUsuario) {
        return productosRepo.findByUsuario_IdUsuario(idUsuario.longValue()).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public productosRes actualizarProducto(Integer id, productosReq request) {
        productos productoExistente = productosRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        estados estado = estadosRepo.findById(request.getIdEstado().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        if (!productoExistente.getNombre().equals(request.getNombre()) &&
                productosRepo.existsByNombreAndUsuario_IdUsuario(request.getNombre(), productoExistente.getUsuario().getIdUsuario())) {
            throw new IllegalArgumentException("Ya existe un producto con este nombre");
        }

        productoExistente.setNombre(request.getNombre());
        productoExistente.setStock(request.getStock());
        productoExistente.setImagen(request.getImagen());
        productoExistente.setPrecio(request.getPrecio());
        productoExistente.setEstado(estado);

        productos productoActualizado = productosRepo.save(productoExistente);

        if (request.getTallas() != null) {
            actualizarTallasDeProducto(productoActualizado, request.getTallas());
        }

        return convertirAResponse(productoActualizado);
    }

    @Override
    @Transactional
    public void eliminarProducto(Integer id) {
        if (!productosRepo.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        productosRepo.deleteById(id);
    }

    @Override
    @Transactional
    public productosRes agregarTallaAProducto(Integer idProducto, Integer idTalla) {
        productos producto = productosRepo.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        tallas talla = tallasRepo.findById(idTalla)
                .orElseThrow(() -> new ResourceNotFoundException("Talla no encontrada"));

        if (productosTallasRepo.existsByProducto_IdProductoAndTalla_IdTalla(idProducto, idTalla)) {
            throw new IllegalArgumentException("Esta talla ya está asignada al producto");
        }

        productos_tallas relacion = new productos_tallas();
        relacion.setProducto(producto);
        relacion.setTalla(talla);
        productosTallasRepo.save(relacion);

        return convertirAResponse(producto);
    }

    @Override
    @Transactional
    public productosRes removerTallaDeProducto(Integer idProducto, Integer idTalla) {
        productos_tallas relacion = productosTallasRepo.findByProducto_IdProductoAndTalla_IdTalla(idProducto, idTalla)
                .orElseThrow(() -> new ResourceNotFoundException("Esta talla no está asignada al producto"));

        productosTallasRepo.delete(relacion);

        productos producto = productosRepo.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return convertirAResponse(producto);
    }

    private void asignarTallasAProducto(productos producto, List<Integer> idTallas) {
        idTallas.forEach(idTalla -> {
            tallas talla = tallasRepo.findById(idTalla)
                    .orElseThrow(() -> new ResourceNotFoundException("Talla no encontrada: " + idTalla));

            if (!productosTallasRepo.existsByProducto_IdProductoAndTalla_IdTalla(producto.getIdProducto(), idTalla)) {
                productos_tallas relacion = new productos_tallas();
                relacion.setProducto(producto);
                relacion.setTalla(talla);
                productosTallasRepo.save(relacion);
            }
        });
    }

    private void actualizarTallasDeProducto(productos producto, List<Integer> idTallas) {
        // Eliminar relaciones existentes no incluidas en la nueva lista
        productosTallasRepo.findAllByProducto_IdProducto(producto.getIdProducto())
                .forEach(relacion -> {
                    if (!idTallas.contains(relacion.getTalla().getIdTalla())) {
                        productosTallasRepo.delete(relacion);
                    }
                });

        // Agregar nuevas relaciones
        asignarTallasAProducto(producto, idTallas);
    }

    private productosRes convertirAResponse(productos producto) {
        List<tallasRes> tallasResponse = productosTallasRepo.findAllByProducto_IdProducto(producto.getIdProducto())
                .stream()
                .map(relacion -> tallasRes.builder()
                        .idTalla(relacion.getTalla().getIdTalla())
                        .talla(relacion.getTalla().getTalla()) // Cambiado a getTalla()
                        .build())
                .collect(Collectors.toList());

        return productosRes.builder()
                .idProducto(producto.getIdProducto())
                .idUsuario(producto.getUsuario().getIdUsuario().intValue())
                .nombreUsuario(producto.getUsuario().getNombre() + " " + producto.getUsuario().getApellido())
                .nombre(producto.getNombre())
                .stock(producto.getStock())
                .imagen(producto.getImagen())
                .precio(producto.getPrecio())
                .estado(producto.getEstado().getNombreEstado())
                .fechaAgregado(producto.getFechaAgregado())
                .tallas(tallasResponse)
                .build();
    }
}