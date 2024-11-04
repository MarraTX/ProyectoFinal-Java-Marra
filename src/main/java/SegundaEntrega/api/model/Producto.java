package SegundaEntrega.api.model;

import java.util.Set;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "producto_tienda",
    joinColumns = @JoinColumn(name = "producto_id"),
    inverseJoinColumns = @JoinColumn(name = "tienda_id")
)
    private Set<Fango> tiendas;

    public Producto(){

    }

    public Producto(Long id, String nombre, Double precio, int stock, String categoria, Set<Fango> tiendas) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.tiendas = tiendas;
    }

    public Set<Fango> getTiendas() {
        return tiendas;
    }

    public void setTiendas(Set<Fango> tiendas) {
        this.tiendas = tiendas;
    }

    

}
