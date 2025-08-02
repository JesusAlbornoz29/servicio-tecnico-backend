package com.miempresa.serviciotecnico.controller;


import com.miempresa.serviciotecnico.model.Producto;
import com.miempresa.serviciotecnico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Producto> productos = productoRepository.findAll();
        return ResponseEntity.ok(productos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con ID: " + id);
        }
    }


    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> getByCodigo(@PathVariable String codigo) {
        Optional<Producto> producto = productoRepository.findByCodigo(codigo);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con código: " + codigo);
        }
    }


    @GetMapping("/serie/{numeroSerie}")
    public ResponseEntity<?> getByNumeroSerie(@PathVariable String numeroSerie) {
        Optional<Producto> producto = productoRepository.findByNumeroSerie(numeroSerie);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con numero de serie: " + numeroSerie);
        }
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
