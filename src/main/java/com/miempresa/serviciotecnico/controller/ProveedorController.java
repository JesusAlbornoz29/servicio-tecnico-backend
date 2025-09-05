package com.miempresa.serviciotecnico.controller;

import com.miempresa.serviciotecnico.model.Proveedor;
import com.miempresa.serviciotecnico.repository.ProveedorRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);

        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable String nombre) {
        Optional<Proveedor> proveedor = proveedorRepository.findByNombre(nombre);

        if(proveedor.isPresent()){
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con nombre: " + nombre);
        }
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El nombre del proveedor es obligatorio.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proveedorRepository.save(proveedor));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Proveedor datos) {
        Optional<Proveedor> optional = proveedorRepository.findById(id);

        if (optional.isPresent()) {
            Proveedor proveedor = optional.get();
            if (datos.getNombre() != null) proveedor.setNombre(datos.getNombre());
            if (datos.getDireccion() != null) proveedor.setDireccion(datos.getDireccion());
            if (datos.getTelefono() != null) proveedor.setTelefono(datos.getTelefono());
            if (datos.getEmail() != null) proveedor.setEmail(datos.getEmail());
            return ResponseEntity.ok(proveedorRepository.save(proveedor));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.ok("Proveedor eliminado con exito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);
        }
    }

    }
