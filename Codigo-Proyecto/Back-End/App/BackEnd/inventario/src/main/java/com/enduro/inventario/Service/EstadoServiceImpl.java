package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;
import com.enduro.inventario.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoServiceImpl implements  EstadoService{

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> getEstado() {
        return estadoRepository.findAll();
    }

    @Override
    public void saveEstado(Estado estado) {

        estadoRepository.save(estado);

    }

    @Override
    public boolean editestado(String nombreEstado, String nombreNuevo) {

        Optional<Estado> estadoOptional = findByNombreEstado(nombreEstado);

        if (estadoOptional.isPresent()){

            Estado estadoProv = estadoOptional.get();
            estadoProv.setNombreEstado(nombreNuevo);
            saveEstado(estadoProv);

            return true;

        }

        return false;
    }

    @Override
    public boolean deleteEstado(String nombreEstado) {
        Optional<Estado> estadoOptional = findByNombreEstado(nombreEstado);
        if (estadoOptional.isPresent()) {
            Estado estadoProv = estadoOptional.get();
            estadoRepository.deleteById(estadoProv.getIdEstado());
        }
        return false;
    }

    @Override
    public Optional<Estado> findByNombreEstado(String nombreEstado) {
        return estadoRepository.findByNombreEstado(nombreEstado);
    }
}
