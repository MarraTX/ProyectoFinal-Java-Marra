package SegundaEntrega.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SegundaEntrega.api.model.Cliente;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long> {

    List <Cliente> getClientsByName(Cliente cliente);

}
