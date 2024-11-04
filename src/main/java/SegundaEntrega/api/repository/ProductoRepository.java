package SegundaEntrega.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SegundaEntrega.api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository <Producto,Long> {

}
