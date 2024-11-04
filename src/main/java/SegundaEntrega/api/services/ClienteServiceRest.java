package SegundaEntrega.api.services;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.mapper.ClienteMapper;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.Fango;
import SegundaEntrega.api.repository.ClienteRepository;
import SegundaEntrega.api.repository.FangoRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteServiceRest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final ClienteMapper clienteMapper;
    @Autowired
    private RestTemplate restTemplate;

    public ClienteServiceRest(ClienteRepository clienteRepository, FangoRepository tiendaRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Autowired
    private FangoRepository tiendaRepository;

    public List<ClienteDTO> getAllClients() {
        List<ClienteDTO> clientesDB = clienteRepository.findAll().stream()
                .map(clienteMapper::toDTOCliente)
                .collect(Collectors.toList());

        Cliente[] clientesAPI = restTemplate.getForObject(BASE_URL, Cliente[].class);

        if (clientesAPI != null) {
            for (Cliente cliente : clientesAPI) {
                clientesDB.add(clienteMapper.toDTOCliente(cliente));
            }
        }

        return clientesDB;
    }

    public ClienteDTO getClientById(Long id) {
        Optional<Cliente> optionalClient = clienteRepository.findById(id);

        if (optionalClient.isPresent()) {
            return clienteMapper.toDTOCliente(optionalClient.get());
        } else {
            // Si el cliente no está en la base de datos, buscar en la API externa
            ClienteDTO clienteDTO = restTemplate.getForObject(BASE_URL + "/{id}", ClienteDTO.class, id);
            if (clienteDTO != null) {
                return clienteDTO;
            } else {
                throw new RuntimeException("Cliente no encontrado ni en la base de datos ni en la API externa");
            }
        }
    }

    @Transactional
    public ClienteDTO saveClientFromApi(Long id) {
        ClienteDTO clienteDTO = restTemplate.getForObject(BASE_URL + "/{id}", ClienteDTO.class, id);

        if (clienteDTO != null) {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            Cliente savedCliente = clienteRepository.save(cliente);
            return clienteMapper.toDTOCliente(savedCliente);
        } else {
            throw new RuntimeException("Cliente no encontrado en la API con ID: " + id);
        }
    }
    public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
        // Crear un nuevo cliente a partir del DTO
        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        // Si el DTO contiene IDs de tiendas, busca las tiendas correspondientes
        if (clienteDTO.getTiendaIds() != null && !clienteDTO.getTiendaIds().isEmpty()) {
            Set<Fango> tiendas = new HashSet<>();

            for (Long tiendaId : clienteDTO.getTiendaIds()) {
                // Busca cada tienda por su ID
                Optional<Fango> optionalTienda = tiendaRepository.findById(tiendaId);

                // Si la tienda existe, la añade al conjunto
                optionalTienda.ifPresent(tiendas::add);
            }

            // Asigna las tienda encontradas al producto
            cliente.setTiendas(tiendas);
        }

        // Guarda el producto en la base de datos
        Cliente savedCliente = clienteRepository.save(cliente);

        // Retorna el DTO del producto guardado
        return clienteMapper.toDTOCliente(savedCliente);
    }

    // Eliminar un cliente en la base de datos y en la API externa
    public void deleteClient(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);

            // Eliminar el cliente en la API externa
            restTemplate.delete(BASE_URL + "/{id}", id);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }

    public ClienteDTO updaClienteDTO(Long id, ClienteDTO clienteDTO){
        return clienteRepository.findById(id)
        .map(cliente -> {
            cliente.setName(clienteDTO.getName());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setPhone(clienteDTO.getPhone());
            return clienteMapper.toDTOCliente(clienteRepository.save(cliente));
        })
        .orElse(null);
    }

}
