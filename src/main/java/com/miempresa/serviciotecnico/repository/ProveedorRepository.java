package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByNombre (String nombre);
}
