package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_tienda",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "tienda_id")
    )
    private Set<Fango> tiendas = new HashSet<>();

    public Cliente(){

    }

    public Cliente(Long id, String name, String email, String phone, Set<Fango> tiendas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.tiendas = tiendas;
    }

    public Set<Fango> getTiendas() {
        return tiendas;
    }

    public void setTiendas(Set<Fango> tiendas) {
        this.tiendas = tiendas;
    }
}

