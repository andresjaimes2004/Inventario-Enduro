package com.enduro.inventario.entity;

<<<<<<< HEAD
public class productos_tallas {
}
=======
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTOS_TALLAS")
@IdClass(ProductoTallaId.class)
public class productos_tallas {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private productos producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_talla", referencedColumnName = "id_talla")
    private tallas talla;
}

// Clase para la clave compuesta
class ProductoTallaId implements java.io.Serializable {
    private Integer producto;
    private Integer talla;
}
>>>>>>> rama-secundaria
