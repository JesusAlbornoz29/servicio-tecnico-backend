package com.miempresa.serviciotecnico.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class IngresoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaIngreso;
    private String problemaReportado;
    private Boolean enGarantia;
    private String garantiaProveedor;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Producto producto;
}
