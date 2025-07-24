package com.miempresa.serviciotecnico.controller;


import com.miempresa.serviciotecnico.model.Cliente;
import com.miempresa.serviciotecnico.repository.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteConroller {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("cedula/{cedula}")
    public ResponseEntity<?> findByCedula(@PathVariable String cedula) {
        return clienteRepository.findByCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("{id}")
    public Cliente updateClienteCompleto(@PathVariable Long id, @RequestBody Cliente clienteActualizado){
        return clienteRepository.findById(id)
                .map(c -> {
                    c.setCedula(clienteActualizado.getCedula());
                    c.setNombre(clienteActualizado.getNombre());
                    c.setDireccion(clienteActualizado.getDireccion());
                    c.setCiudad(clienteActualizado.getCiudad());
                    c.setDepartamento(clienteActualizado.getDepartamento());
                    c.setTelefono(clienteActualizado.getTelefono());
                    c.setEmail(clienteActualizado.getEmail());
                    return clienteRepository.save(c);
                }).orElse(null);
    }

    @PatchMapping("/{id}")
    public Cliente patchCliente(@PathVariable Long id, @RequestBody Cliente datos) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    if (datos.getCedula() != null) clienteExistente.setCedula(datos.getCedula());
                    if (datos.getNombre() != null) clienteExistente.setNombre(datos.getNombre());
                    if (datos.getDireccion() != null) clienteExistente.setDireccion(datos.getDireccion());
                    if (datos.getCiudad() != null) clienteExistente.setCiudad(datos.getCiudad());
                    if (datos.getDepartamento() != null) clienteExistente.setDepartamento(datos.getDepartamento());
                    if (datos.getTelefono() != null) clienteExistente.setTelefono(datos.getTelefono());
                    if (datos.getEmail() != null) clienteExistente.setEmail(datos.getEmail());
                    return clienteRepository.save(clienteExistente);
                }).orElse(null);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}

