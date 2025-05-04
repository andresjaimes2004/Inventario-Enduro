package com.enduro.inventario.entity;

<<<<<<< HEAD
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
=======
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
>>>>>>> rama-secundaria
@Entity
@Table(name = "usuarios")
public class usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "id_usuario" )
    private Long id_usuario;

    @Column(Nullable = false, length = 100)
    private String nombre;

    @Column(Nullable = false, length = 100)
    private String apellido;

    @Column(Nullable = false, length = 150)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(Nullable = false)
=======
    @Column(name = "id_usuario")
    private Long idUsuario;  // Cambiado a camelCase

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 150)
    private String password;  // Cambiado de "contraseña" a "password"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
>>>>>>> rama-secundaria
    private String rol;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
<<<<<<< HEAD
    private String estado;

    @Column(name = "fecha_creacion",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion=new Date();
}
=======
    private estados estado;  // Relación corregida (debe apuntar a la entidad estados)

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();
}
>>>>>>> rama-secundaria
