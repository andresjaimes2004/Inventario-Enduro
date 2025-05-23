package com.enduro.inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 40)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", nullable = false, length = 40)
    private String apellidoUsuario;

    @Column(name = "nick_usuario", nullable = false, unique = true, length = 50)
    private String nickUsuario;

    @Column(name = "contraseña_usuario", nullable = false, length = 60)
    private String contraseñaUsuario;

    @Column(name = "correo_usuario", nullable = false, unique = true, length = 60)
    private String correoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estadoUsuario;

    @Column(name = "fecha_usuario_creado", nullable = false, updatable = false)
    private LocalDateTime fechaUsuarioCreado;


}
