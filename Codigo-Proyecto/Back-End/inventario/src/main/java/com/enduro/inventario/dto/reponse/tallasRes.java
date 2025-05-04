package com.enduro.inventario.dto.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
@Schema(description = "DTO de respuesta para tallas")
public class tallasRes {

    @Schema(description = "ID único de la talla", example = "1")
    private Integer idTalla;

    @Schema(description = "Valor numérico de la talla (entre 25 y 43)", example = "38")
    private Integer talla;

    @Schema(description = "Lista de productos asociados a esta talla")
    private List<ProductoSimpleRes> productos;

    @Data
    @Schema(description = "Representación simplificada de producto para asociaciones")
    public static class ProductoSimpleRes {
        @Schema(description = "ID del producto", example = "101")
        private Integer idProducto;

        @Schema(description = "Nombre del producto", example = "Zapato deportivo")
        private String nombre;

        @Schema(description = "URL de la imagen del producto", example = "https://example.com/image.jpg")
        private String imagenUrl;
    }
}