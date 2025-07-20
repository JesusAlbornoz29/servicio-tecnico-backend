package com.miempresa.serviciotecnico.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ComentarioIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;
    private String autor;
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "ingreso_id")
    @JsonIgnore
    private IngresoServicio ingresoServicio;
}
