package com.enduro.inventario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estados")
@Data
public class estados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

<<<<<<< HEAD
    @Column(nullable = false, length = 50)
    private String nombreEstado;

    @Column(length = 100)
    private String descripcion;
=======
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, unique = true)
    private String tipoEntidad;

    @Column(nullable = false, length = 50)
    private String nombreEstado;

>>>>>>> rama-secundaria
}
