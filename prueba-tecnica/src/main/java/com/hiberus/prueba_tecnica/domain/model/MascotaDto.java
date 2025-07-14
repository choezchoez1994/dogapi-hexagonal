package com.hiberus.prueba_tecnica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDto implements Serializable {
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
    private RazaDto razaDto;
}
