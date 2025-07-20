package com.miempresa.serviciotecnico.controller;

import com.miempresa.serviciotecnico.model.ComentarioIngreso;
import com.miempresa.serviciotecnico.repository.ComentarioIngresoRepository;
import com.miempresa.serviciotecnico.repository.IngresoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioIngresoController {

    @Autowired
    private ComentarioIngresoRepository comentarioIngresoRepository;

    @Autowired
    private IngresoServicioRepository ingresoServicioRepository;

    @PostMapping("/{ingresoId}")
    public ComentarioIngreso agregarComentario(@PathVariable Long ingresoId, @RequestBody ComentarioIngreso comentario) {
        return ingresoServicioRepository.findById(ingresoId).map(ingreso -> {
            comentario.setIngresoServicio(ingreso);
            comentario.setFechaHora(LocalDateTime.now());
            return comentarioIngresoRepository.save(comentario);
        }).orElse(null);
    }

    @GetMapping("/{ingresoId}")
    public List<ComentarioIngreso> listarComentarios(@PathVariable Long ingresoId) {
        return comentarioIngresoRepository.findByIngresoServicioId(ingresoId);
    }

    @DeleteMapping("/{id}")
    public void eliminarComentario(@PathVariable Long id) {
        comentarioIngresoRepository.deleteById(id);
    }

}
