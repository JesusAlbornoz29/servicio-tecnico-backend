package com.miempresa.serviciotecnico.repository;

import com.miempresa.serviciotecnico.model.ComentarioIngreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioIngresoRepository extends JpaRepository<ComentarioIngreso, Long> {
    List<ComentarioIngreso> findByIngresoServicioId(Long ingresoId);
}

