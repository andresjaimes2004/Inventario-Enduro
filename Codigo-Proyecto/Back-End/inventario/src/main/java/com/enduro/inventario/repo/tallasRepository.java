package com.enduro.inventario.repo;

import com.enduro.inventario.entity.tallas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface tallasRepository extends JpaRepository<tallas, Integer> {

    // Busca una talla por su valor numérico
    Optional<tallas> findByTalla(Integer talla);

    // Verifica si existe una talla con ese valor
    boolean existsByTalla(Integer talla);

    // Busca tallas dentro de un rango (opcional)
    List<tallas> findByTallaBetween(Integer minTalla, Integer maxTalla);
}