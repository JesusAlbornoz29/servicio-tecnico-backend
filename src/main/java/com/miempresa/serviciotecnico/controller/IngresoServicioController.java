package com.miempresa.serviciotecnico.controller;

import com.miempresa.serviciotecnico.model.IngresoServicio;
import com.miempresa.serviciotecnico.repository.ClienteRepository;
import com.miempresa.serviciotecnico.repository.IngresoServicioRepository;
import com.miempresa.serviciotecnico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresos")
public class IngresoServicioController {

    @Autowired
    private IngresoServicioRepository ingresoServicioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<IngresoServicio> getAll() {
        return ingresoServicioRepository.findAll();
    }

    @GetMapping("/{id}")
    public IngresoServicio getById(@PathVariable Long id){
        return ingresoServicioRepository.findById(id).orElse(null);
    }


    @PostMapping
    public IngresoServicio create (@RequestBody IngresoServicio ingreso){
        if(ingreso.getCliente() != null && ingreso.getProducto() != null){
            return ingresoServicioRepository.save(ingreso);
        }
        return null;
    }

}
