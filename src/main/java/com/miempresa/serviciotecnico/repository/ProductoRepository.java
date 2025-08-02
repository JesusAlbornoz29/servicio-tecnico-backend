package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository <Producto, Long> {
    Optional<Producto> findByNumeroSerie(String numeroSerie);
    boolean existsByNumeroSerie(String numeroSerie);
    Optional<Producto> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}
