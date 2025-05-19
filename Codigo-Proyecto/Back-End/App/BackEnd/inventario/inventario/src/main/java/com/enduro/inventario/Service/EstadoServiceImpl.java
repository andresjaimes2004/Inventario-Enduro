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
    public void editestado(Estado estado) {
        this.saveEstado(estado);
    }

    @Override
    public void deleteEstado(Estado estado) {

        estadoRepository.deleteById(estado.getIdEstado());

    }
}
