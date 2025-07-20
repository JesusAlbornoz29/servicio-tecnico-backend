package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository <Producto, Long> {
    Optional<Producto> findByNumeroSerie(String numeroSerie);
}
