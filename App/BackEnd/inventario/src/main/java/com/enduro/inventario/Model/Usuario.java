package com.enduro.inventario.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
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
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estadoUsuario;

    @Column(name = "fecha_usuario_creado", nullable = false, updatable = false)
    private LocalDate fechaUsuarioCreado;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String nickUsuario, String contraseñaUsuario, String correoUsuario, Estado estadoUsuario, LocalDate fechaUsuarioCreado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.nickUsuario = nickUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.correoUsuario = correoUsuario;
        this.estadoUsuario = estadoUsuario;
        this.fechaUsuarioCreado = fechaUsuarioCreado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Estado getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Estado estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public LocalDate getFechaUsuarioCreado() {
        return fechaUsuarioCreado;
    }

    public void setFechaUsuarioCreado(LocalDate fechaUsuarioCreado) {
        this.fechaUsuarioCreado = fechaUsuarioCreado;
    }

    @PrePersist
    public void prePersist() {
        if (fechaUsuarioCreado == null) {
            this.fechaUsuarioCreado = LocalDate.now(); // Asigna la fecha actual si no se proporciona
        }
    }

}
