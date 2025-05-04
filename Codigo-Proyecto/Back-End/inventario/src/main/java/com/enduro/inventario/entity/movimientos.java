package com.enduro.inventario.entity;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import java.util.ArrayList;
=======
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
>>>>>>> rama-secundaria

@Data
@Entity
@Table(name = "MOVIMIENTOS")
public class movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer id_movimiento;

    private Integer id_usuario;
    private Integer id_producto;
=======
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private productos producto;
>>>>>>> rama-secundaria

    @Column(nullable = false, length = 30)
    private String razon;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(nullable = false)
    private Integer cantidad;
<<<<<<< HEAD
    private String fecha_movimiento;

    public enum Razon {
        VENTA, COMPRA, AJUSTE
    }

    public enum Tipo {
        ENTRADA, SALIDA
    }

    // Getters y setters
    public Integer getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Integer id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Razon getRazon() {
        return razon;
    }

    public void setRazon(Razon razon) {
        this.razon = razon;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }
}

=======

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private estados estado;

    @Column(name = "fecha_movimiento", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento = new Date();
}
>>>>>>> rama-secundaria
