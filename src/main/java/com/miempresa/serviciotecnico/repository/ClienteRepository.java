package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCedula(String cedula);
}
