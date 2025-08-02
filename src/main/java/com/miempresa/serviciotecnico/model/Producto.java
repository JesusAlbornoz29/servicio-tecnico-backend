package com.miempresa.serviciotecnico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true, nullable = false)
    private String codigo;

    private String tipo;
    private String marca;
    private String modelo;

    @Column(unique = true)
    private String numeroSerie;

    @Override
    public String toString() {
        return tipo + " " + marca + " " + modelo;
    }
}
