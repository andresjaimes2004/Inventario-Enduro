package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Repository.EstadoRepository;
import com.enduro.inventario.Repository.ProductoRepository;
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


}
