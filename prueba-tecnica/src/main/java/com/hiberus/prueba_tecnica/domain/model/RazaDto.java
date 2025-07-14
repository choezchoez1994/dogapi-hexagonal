package com.hiberus.prueba_tecnica.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RazaDto implements Serializable {

    private Long id;
    private String name;
    @JsonProperty("bred_for")
    private String bredFor;
    @JsonProperty("breed_group")
    private String breedGroup;
    @JsonProperty("life_span")
    private String lifeSpan;
    private String temperament;
    private String origin;
    @JsonProperty("reference_image_id")
    private String referenceImageId;
    private WeightHeight weight;
    private WeightHeight height;

    private Double promedioPeso;
    private Double promedioAltura;


}
