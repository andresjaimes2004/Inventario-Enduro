package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Model.Producto;
import com.enduro.inventario.Model.ProductoTalla;
import com.enduro.inventario.Model.Talla;
import com.enduro.inventario.Repository.EstadoRepository;
import com.enduro.inventario.Repository.ProductoRepository;
import com.enduro.inventario.Repository.ProductoTallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoTallaImpl implements ProductoTallaService{

    @Autowired
    private ProductoTallaRepository productoTallaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<ProductoTalla> GetProductoTalla() {
        return productoTallaRepository.findAll();
    }

    @Override
    public void saveProductoTalla(ProductoTalla productoTalla) {
        productoTallaRepository.save(productoTalla);
    }

    @Override
    public void editProductoTalla(ProductoTalla productoTalla) {
        this.saveProductoTalla(productoTalla);
    }

    @Override
    public boolean deleteProductoTalla(ProductoTalla productoTalla) {
        Optional<Estado> estadoOptional=estadoRepository.findByNombreEstado("Eliminado");
        if(estadoOptional.isPresent()){
            productoTalla.setEstado(estadoOptional.get());
            return true;
        }


        return false;
    }

    @Override
    public List<ProductoTalla> findByProducto(String nombreProducto) {
        Optional<Producto> productoOptional =  productoRepository.findByNombreProducto(nombreProducto);
        Producto producto = productoOptional.get();
        Integer id_producto= producto.getIdProducto();
        return productoTallaRepository.findByProductoIdProducto(id_producto);
    }


}
