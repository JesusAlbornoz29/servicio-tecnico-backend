package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
