package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.FangoCreateDTO;
import SegundaEntrega.api.DTO.FangoDTO;
import SegundaEntrega.api.model.Fango;

import java.util.stream.Collectors;

@Component
public class FangoMapper {

    private final ClienteMapper clienteMapper;
    private final ProductoMapper productoMapper;

    public FangoMapper(ClienteMapper clienteMapper, ProductoMapper productoMapper) {
        this.clienteMapper = clienteMapper;
        this.productoMapper = productoMapper;
    }

    public FangoDTO toDTOTienda(Fango tienda, boolean includeRelations) {
        if (tienda == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        FangoDTO.FangoDTOBuilder builder = FangoDTO.builder()
                .id(tienda.getId())
                .nombre(tienda.getNombre())
                .direccion(tienda.getDireccion())
                .telefono(tienda.getTelefono());

        if (includeRelations) {
            builder.clientes(tienda.getClientes().stream()
                    .map(clienteMapper::toDTOCliente)
                    .collect(Collectors.toSet()));
            builder.productos(tienda.getProductos().stream()
                    .map(productoMapper::toDTOProducto)
                    .collect(Collectors.toSet()));
        }

        return builder.build();
    }

    public Fango toEntity(FangoCreateDTO tiendaCreateDTO) {
    if (tiendaCreateDTO == null) {
        throw new IllegalArgumentException("La ptiendaCreateDTO no puede ser nula");
    }

    Fango tienda = new Fango();
    tienda.setId(tiendaCreateDTO.getId());
    tienda.setNombre(tiendaCreateDTO.getNombre());
    tienda.setDireccion(tiendaCreateDTO.getDireccion());
    tienda.setTelefono(tiendaCreateDTO.getTelefono());
    // No asignar relaciones en la creación o actualización.
    return tienda;
}
}
