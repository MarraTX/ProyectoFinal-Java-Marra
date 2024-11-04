package SegundaEntrega.api.services;

import java.util.List;

import SegundaEntrega.api.DTO.ClienteDTO;

public interface ClienteService {
    ClienteDTO getClientById(Long id);

    List<ClienteDTO> getAllClients();

    ClienteDTO saveClienteDTO(ClienteDTO clienteDTO);

    ClienteDTO saveClientFromApi(ClienteDTO clienteDTO);

    void deleteClient(Long id);
}
