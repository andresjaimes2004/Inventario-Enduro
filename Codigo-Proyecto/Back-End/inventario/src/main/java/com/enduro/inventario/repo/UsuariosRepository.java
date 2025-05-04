package com.enduro.inventario.repo;

import com.enduro.inventario.entity.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


<<<<<<< HEAD
=======
import java.util.List;
import java.util.Optional;


>>>>>>> rama-secundaria
@Repository
public interface UsuariosRepository extends JpaRepository<usuarios, Long> {
    Optional<usuarios> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
<<<<<<< HEAD
=======
    List<usuarios> findByNombreContainingIgnoreCase(String nombre);
>>>>>>> rama-secundaria
}
