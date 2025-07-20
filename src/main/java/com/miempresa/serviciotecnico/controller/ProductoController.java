package com.miempresa.serviciotecnico.controller;


import com.miempresa.serviciotecnico.model.Producto;
import com.miempresa.serviciotecnico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }


    @PutMapping ("/{id}")
    public Producto update (@PathVariable Long id, @RequestBody Producto datos){
        return productoRepository.findById(id)
                .map(p -> {
                    p.setTipo(datos.getTipo());
                    p.setMarca(datos.getMarca());
                    p.setModelo(datos.getModelo());
                    p.setNumeroSerie(datos.getNumeroSerie());
                    return productoRepository.save(p);
                }).orElse(null);
    }

    @PatchMapping("/{id}")
    public Producto patch(@PathVariable Long id, @RequestBody Producto datos){
        return productoRepository.findById(id)
                .map(p -> {
                    if (datos.getTipo() != null) p.setTipo(datos.getTipo());
                    if (datos.getMarca() != null) p.setMarca(datos.getMarca());
                    if (datos.getModelo() != null) p.setModelo(datos.getModelo());
                    if (datos.getNumeroSerie() != null) p.setNumeroSerie(datos.getNumeroSerie());
                    return productoRepository.save(p);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }


}
