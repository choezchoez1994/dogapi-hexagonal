package com.hiberus.prueba_tecnica.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int edad;
    private String direccion;
    private String ciudad;
    private String nombreRaza;
    private String grupo;
    private String temperamento;
    private String proposito;
    private String edadPromedio;
    private Double pesoPromedio;
    private Double alturaPromedio;

}
