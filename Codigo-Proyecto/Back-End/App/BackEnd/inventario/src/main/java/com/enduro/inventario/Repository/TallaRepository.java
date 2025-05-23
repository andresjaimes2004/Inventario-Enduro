package com.enduro.inventario.Repository;

import com.enduro.inventario.Model.Talla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TallaRepository extends JpaRepository<Talla, Integer> {

    Talla findByTalla(String nTalla);

}
