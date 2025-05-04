package com.enduro.inventario.dto.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class productosRes {
    @Schema(description = "ID del producto", example = "1")
    private Integer idProducto;

    @Schema(description = "ID del usuario creador", example = "1")
    private Integer idUsuario;

    @Schema(description = "Nombre del usuario creador", example = "Juan Pérez")
    private String nombreUsuario;

    @Schema(description = "Nombre del producto", example = "Zapato deportivo")
    private String nombre;

    @Schema(description = "Cantidad en stock", example = "10")
    private Integer stock;

    @Schema(description = "URL de la imagen del producto")
    private String imagen;

    @Schema(description = "Precio del producto", example = "89.99")
    private Double precio;

    @Schema(description = "Estado del producto", example = "Disponible")
    private String estado;

    @Schema(description = "Fecha de creación del producto")
    private Date fechaAgregado;

    @Schema(description = "Lista de tallas disponibles")
    private List<tallasRes> tallas;
}