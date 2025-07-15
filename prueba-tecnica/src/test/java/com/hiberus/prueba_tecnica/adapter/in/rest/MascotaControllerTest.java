package com.hiberus.prueba_tecnica.adapter.in.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import com.hiberus.prueba_tecnica.domain.port.in.RegistrarMascotaUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MascotaController.class)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrarMascotaUseCase registrarMascotaUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegistrarMascota() throws Exception {
        MascotaDto dto = MascotaDto.builder()
                .nombre("Luna")
                .edad(2)
                .direccion("Av. 2")
                .ciudad("Quito")
                .nombreRaza("Beagle")
                .grupo("Hound")
                .temperamento("Amigable")
                .proposito("Caza")
                .edadPromedio("13 - 15")
                .pesoPromedio(20.00)
                .alturaPromedio(35.00)
                .build();

        when(registrarMascotaUseCase.registrarMascota(any())).thenReturn(dto);

        mockMvc.perform(post("/mascotas/registrar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Luna"))
                .andExpect(jsonPath("$.nombreRaza").value("Beagle"));
    }

    @Test
    void testBuscarPorFiltros() throws Exception {
        Mascota dto = Mascota.builder()
                .nombre("Rocky")
                .edad(5)
                .direccion("Av. 9")
                .ciudad("Guayaquil")
                .nombreRaza("Labrador")
                .grupo("Sporting")
                .temperamento("Friendly")
                .proposito("Companion")
                .edadPromedio("12 - 14")
                .pesoPromedio(30.00)
                .alturaPromedio(55.00)
                .build();

        when(registrarMascotaUseCase.buscarPorFiltros(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/mascotas/buscar?raza=Labrador"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Rocky"))
                .andExpect(jsonPath("$[0].nombreRaza").value("Labrador"));
    }
}
