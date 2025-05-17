package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.Talla;
import com.enduro.inventario.Repository.EstadoRepository;
import com.enduro.inventario.Repository.ProductoRepository;
import com.enduro.inventario.Repository.TallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    public List<Producto> GetProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void saveProducto(Producto producto) {

        productoRepository.save(producto);

    }

    @Override
    public boolean editProducto(Integer id, String nombreProducto, String imagen, String Talla, Integer cantidad, BigDecimal precio, String estado) {

        Optional<Talla> tallaOptional=tallaRepository.findByNTalla(Talla);
        Talla tallaProv=tallaOptional.get();

        Optional<Producto> productoOptional=productoRepository.findByIdProductoAndTalla(id,tallaProv);

        if (productoOptional.isPresent()){

            Producto producto=productoOptional.get();

            producto.setNombreProducto(nombreProducto);
            producto.setImagen(imagen);
            producto.setTalla(tallaProv);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteProducto(String nombreProducto, String talla) {
        Optional<Producto> productoOptional=productoRepository.findByNombreProducto(nombreProducto);
        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Eliminado");
        Optional<Talla> tallaOptional=tallaRepository.findByNTalla(talla);

        if (productoOptional.isPresent() && estadoOptional.isPresent() && tallaOptional.isPresent()){

            Producto producto=productoOptional.get();
            producto.setEstado(estadoOptional.get());
            productoRepository.save(producto);
            return true;

        }

        return false;
    }

    @Override
    public Optional<Producto> findByNombreProductoContaining(String nombreProducto) {
        return productoRepository.findByNombreProductoContaining(nombreProducto);
    }

    @Override
    public Optional<Producto> findByNombreProducto(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto);
    }

    @Override
    public Optional<Producto> findByTalla(String talla) {

        Optional<Talla> tallaOptional=tallaRepository.findByNTalla(talla);
        if (tallaOptional.isPresent()){
            Talla tallaProv=tallaOptional.get();
            return productoRepository.findByTalla(tallaProv);

        }

        return null;
    }
}
