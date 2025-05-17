package com.enduro.inventario.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tallas")
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTalla;

    @Column(name = "n_talla", nullable = false, length = 10)
    private String nTalla;

    public Talla() {
    }

    public Talla(Integer idTalla, String nTalla) {
        this.idTalla = idTalla;
        this.nTalla = nTalla;
    }

    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public String getnTalla() {
        return nTalla;
    }

    public void setnTalla(String nTalla) {
        this.nTalla = nTalla;
    }
}
