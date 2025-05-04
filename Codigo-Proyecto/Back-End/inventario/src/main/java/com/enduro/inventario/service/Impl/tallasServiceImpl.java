package com.enduro.inventario.service.Impl;

import com.enduro.inventario.dto.reponse.tallasRes;
import com.enduro.inventario.dto.request.tallasReq;
import com.enduro.inventario.entity.tallas;
import com.enduro.inventario.exception.ResourceNotFoundException;
import com.enduro.inventario.repo.tallasRepository;
import com.enduro.inventario.service.tallasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class tallasServiceImpl implements tallasService {

    private final tallasRepository tallaRepository;

    @Override
    @Transactional
    public tallasRes crearTalla(tallasReq tallaReq) {
        if (tallaRepository.existsByTalla(tallaReq.getTalla())) {
            throw new IllegalArgumentException("La talla " + tallaReq.getTalla() + " ya existe");
        }

        tallas nuevaTalla = new tallas();
        nuevaTalla.setTalla(tallaReq.getTalla());

        tallas tallaGuardada = tallaRepository.save(nuevaTalla);
        return convertirAResponse(tallaGuardada);
    }

    @Override
    public tallasRes obtenerTallaPorId(Integer id) {
        tallas talla = tallaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Talla no encontrada con ID: " + id));
        return convertirAResponse(talla);
    }

    @Override
    public List<tallasRes> listarTodasTallas() {
        return tallaRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public tallasRes actualizarTalla(Integer id, tallasReq tallaReq) {
        tallas tallaExistente = tallaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Talla no encontrada con ID: " + id));

        if (!tallaExistente.getTalla().equals(tallaReq.getTalla())) {
            if (tallaRepository.existsByTalla(tallaReq.getTalla())) {
                throw new IllegalArgumentException("La talla " + tallaReq.getTalla() + " ya existe");
            }
            tallaExistente.setTalla(tallaReq.getTalla());
        }

        tallas tallaActualizada = tallaRepository.save(tallaExistente);
        return convertirAResponse(tallaActualizada);
    }

    @Override
    @Transactional
    public void eliminarTalla(Integer id) {
        if (!tallaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Talla no encontrada con ID: " + id);
        }
        tallaRepository.deleteById(id);
    }

    @Override
    public List<tallasRes> buscarTallasEnRango(Integer min, Integer max) {
        return tallaRepository.findByTallaBetween(min, max).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public tallasRes buscarPorTallaExacta(Integer talla) {
        return tallaRepository.findByTalla(talla)
                .map(this::convertirAResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Talla " + talla + " no encontrada"));
    }

    @Override
    public boolean existeTalla(Integer talla) {
        return tallaRepository.existsByTalla(talla);
    }

    private tallasRes convertirAResponse(tallas talla) {
        return tallasRes.builder()
                .idTalla(talla.getIdTalla())
                .talla(talla.getTalla())
                .build();
    }
}