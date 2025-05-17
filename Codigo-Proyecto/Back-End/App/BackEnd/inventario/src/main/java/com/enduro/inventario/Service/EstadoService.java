package com.enduro.inventario.Service;

import com.enduro.inventario.Model.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoService {

    public List<Estado> getEstado();

    public void saveEstado(Estado estado);

    public boolean editestado(String nombreEstado, String nombreNuevo);

    public boolean deleteEstado(String nombreEstado);

    public Optional<Estado> findByNombreEstado(String nombreEstado);

}
