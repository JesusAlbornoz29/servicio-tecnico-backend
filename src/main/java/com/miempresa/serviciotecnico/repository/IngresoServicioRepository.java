package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.IngresoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngresoServicioRepository extends JpaRepository<IngresoServicio, Long> {
    List<IngresoServicio> findByClienteCedula(String cedula);
}
