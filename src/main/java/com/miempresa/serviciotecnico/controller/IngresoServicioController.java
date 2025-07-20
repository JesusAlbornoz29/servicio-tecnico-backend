package com.miempresa.serviciotecnico.controller;

import com.miempresa.serviciotecnico.model.Cliente;
import com.miempresa.serviciotecnico.model.IngresoServicio;
import com.miempresa.serviciotecnico.repository.IngresoServicioRepository;
import com.miempresa.serviciotecnico.repository.ClienteRepository;
import com.miempresa.serviciotecnico.repository.ProductoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public IngresoServicio getById(@PathVariable Long id) {
        return ingresoServicioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public IngresoServicio create(@RequestBody IngresoServicio ingreso) {
        if (ingreso.getCliente() != null && ingreso.getProducto() != null) {
            return ingresoServicioRepository.save(ingreso);
        }
        return null;
    }

    @PutMapping("/{id}")
    public IngresoServicio update(@PathVariable Long id, @RequestBody IngresoServicio datos) {
        return ingresoServicioRepository.findById(id)
                .map(i -> {
                    i.setFechaIngreso(datos.getFechaIngreso());
                    i.setProblemaReportado(datos.getProblemaReportado());
                    i.setEnGarantia(datos.getEnGarantia());
                    i.setGarantiaProveedor(datos.getGarantiaProveedor());
                    i.setObservaciones(datos.getObservaciones());

                    if (datos.getCliente() != null) i.setCliente(datos.getCliente());
                    if (datos.getProducto() != null) i.setProducto(datos.getProducto());

                    return ingresoServicioRepository.save(i);
                }).orElse(null);
    }

    @PatchMapping("/{id}")
        public IngresoServicio patch(@PathVariable Long id, @RequestBody IngresoServicio datos){
            return ingresoServicioRepository.findById(id)
                    .map(i -> {
                        if (datos.getFechaIngreso() != null) i.setFechaIngreso(datos.getFechaIngreso());
                        if (datos.getProblemaReportado() != null) i.setProblemaReportado(datos.getProblemaReportado());
                        if (datos.getEnGarantia() != null) i.setEnGarantia(datos.getEnGarantia());
                        if (datos.getGarantiaProveedor() != null) i.setGarantiaProveedor(datos.getGarantiaProveedor());
                        if (datos.getObservaciones() != null) i.setObservaciones(datos.getObservaciones());
                        if (datos.getCliente() != null) i.setCliente(datos.getCliente());
                        if (datos.getProducto() != null) i.setProducto(datos.getProducto());

                        return ingresoServicioRepository.save(i);
                    }).orElse(null);

        }


    @DeleteMapping("/{id}")
       public void delete(@PathVariable Long id){
          ingresoServicioRepository.deleteById(id);
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<?> getIngresosPorCedula(@PathVariable String cedula) {
        Optional<Cliente> cliente = clienteRepository.findByCedula(cedula);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un cliente con esa cédula.");
        }

        List<IngresoServicio> ingresos = ingresoServicioRepository.findByClienteCedula(cedula);
        return ResponseEntity.ok(ingresos);
    }


}