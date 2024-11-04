package SegundaEntrega.api.DTO;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {
    @Schema(description = "Id of the product", example = "0")
    private Long id;
    @Schema(description = "Name of the product", example = "Loaf")
    private String nombre;
    @Schema(description = "Price of the product", example = "3.9")
    private Double precio;
    @Schema(description = "Stock of the product", example = "10")
    private int stock;
    @Schema(description = "Category of the product", example = "bread")
    private String categoria;
    private Set<Long> tiendaIds;

    ProductoDTO(){

    }

    public ProductoDTO(Long id, String nombre, Double precio, int stock, String categoria, Set<Long> tiendaIds) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.tiendaIds = tiendaIds;
    }

    public Set<Long> getTiendaIds() {
        return tiendaIds;
    }

    public void setTiendaIds(Set<Long> tiendaIds) {
        this.tiendaIds = tiendaIds;
    }
}
