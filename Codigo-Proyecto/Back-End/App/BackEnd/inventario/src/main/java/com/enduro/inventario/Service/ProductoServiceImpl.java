package com.enduro.inventario.Service;

import com.enduro.inventario.Model.*;
import com.enduro.inventario.Repository.EstadoRepository;
import com.enduro.inventario.Repository.ProductoRepository;
import com.enduro.inventario.Repository.ProductoTallaRepository;
import com.enduro.inventario.Repository.TallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ProductoTallaRepository productoTallaRepository;

    @Override
    public List<Producto> GetProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void saveProducto(Producto producto) {

        Producto producto1=new Producto(producto.getNombreProducto(), producto.getImagenProducto(), producto.getPrecio(), producto.getEstado());
        productoRepository.save(producto1);

    }

    @Override
    public void editProducto(Producto producto) {
        this.saveProducto(producto);
    }

    @Override
    public boolean deleteProducto(Producto producto) {
        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Inactivo");
        if(estadoOptional.isPresent()){
            producto.setEstado(estadoOptional.get());
            this.saveProducto(producto);
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
    public void saveProductoConTallas(ProductosConTallaDTO DTO) {

        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Activo");
        Estado estado = estadoOptional.get();

        Producto producto = new Producto();
        producto.setNombreProducto(DTO.getNombreProducto());
        producto.setImagenProducto(DTO.getImagenProducto());
        producto.setPrecio(DTO.getPrecio());
        producto.setEstado(estado);

        productoRepository.save(producto);

        for (TallaCantidadDTO tallaDto : DTO.getTallas()) {

            Talla talla = tallaRepository.findByTalla(tallaDto.getNumeroTalla());

            ProductoTalla productoTalla=new ProductoTalla();
            productoTalla.setProducto(producto);
            productoTalla.setTalla(talla);
            productoTalla.setStock(tallaDto.getCantidad());
            productoTalla.setEstado(estado);
            productoTallaRepository.save(productoTalla);

        }

    }


}
