package SegundaEntrega.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SegundaEntrega.api.model.Fango;

@Repository
public interface FangoRepository extends JpaRepository <Fango, Long> {

    Optional<Long> getById(Fango tiendaId);
    
}
