package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.FangoCreateDTO;
import SegundaEntrega.api.DTO.FangoDTO;
import SegundaEntrega.api.mapper.FangoMapper;
import SegundaEntrega.api.model.Fango;
import SegundaEntrega.api.repository.FangoRepository;

@Service
public class FangoService {
    @Autowired
    private final FangoRepository tiendaRepository;
    @Autowired
    private final FangoMapper tiendaMapper;

    public FangoService(FangoMapper tiendaMapper, FangoRepository tiendaRepository) {
        this.tiendaMapper = tiendaMapper;
        this.tiendaRepository = tiendaRepository;
    }

    public List<FangoDTO> getAllTiendas(boolean includeRelations) {
        
        return tiendaRepository.findAll().stream()
                .map(tienda -> tiendaMapper.toDTOTienda(tienda, includeRelations))
                .collect(Collectors.toList());
    }

    public Optional<FangoDTO> getTiendaById(Long id, boolean includeRelations) {
        return tiendaRepository.findById(id)
                .map(tienda -> tiendaMapper.toDTOTienda(tienda, includeRelations));
    }

    public FangoDTO saveTienda(FangoCreateDTO tiendaCreateDTO) {
    Fango tienda = tiendaMapper.toEntity(tiendaCreateDTO);
    Fango savedTienda = tiendaRepository.save(tienda);
    return tiendaMapper.toDTOTienda(savedTienda, false);
}

public FangoDTO updateTienda(Long id, FangoCreateDTO tiendaCreateDTO) {
    return tiendaRepository.findById(id)
        .map(tienda -> {
            tienda.setNombre(tiendaCreateDTO.getNombre());
            tienda.setDireccion(tiendaCreateDTO.getDireccion());
            tienda.setTelefono(tiendaCreateDTO.getTelefono());
            return tiendaMapper.toDTOTienda(tiendaRepository.save(tienda), false);
        })
        .orElse(null);
}

    public void deleteTienda(Long id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
        } else {
            System.out.println("La tienda no existe");
        }
    }
}
