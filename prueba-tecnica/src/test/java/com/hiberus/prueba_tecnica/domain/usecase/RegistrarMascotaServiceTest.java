package com.hiberus.prueba_tecnica.domain.usecase;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import com.hiberus.prueba_tecnica.domain.model.RazaDto;
import com.hiberus.prueba_tecnica.domain.port.out.MascotaRepositoryPort;
import com.hiberus.prueba_tecnica.domain.port.out.RazaServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrarMascotaServiceTest {
    private MascotaRepositoryPort mascotaRepo;
    private RazaServicePort razaService;
    private RegistrarMascotaService service;

    @BeforeEach
    void setup() {
        mascotaRepo = mock(MascotaRepositoryPort.class);
        razaService = mock(RazaServicePort.class);
        service = new RegistrarMascotaService(mascotaRepo, razaService);
    }

    @Test
    void debeRegistrarMascotaConDatosDeRaza() {
        MascotaDto input = MascotaDto.builder()
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
        RazaDto mockRaza = RazaDto.builder()
                .name("Beagle")
                .breedGroup("Hound")
                .temperament("Friendly")
                .build();

        when(razaService.obtenerRazaAPI("Beagle")).thenReturn(mockRaza);
        when(mascotaRepo.guardar(any())).thenAnswer(i -> i.getArgument(0));

        MascotaDto resultado = service.registrarMascota(input);

        assertEquals("Beagle", resultado.getNombreRaza());
        assertEquals("13 - 15", resultado.getEdadPromedio());
        assertEquals("Quito", resultado.getCiudad());
        verify(razaService).obtenerRazaAPI("Beagle");
        verify(mascotaRepo).guardar(any());
    }

    @Test
    void debeBuscarPorFiltros() {
        Mascota mock = Mascota.builder()
                .nombre("Max")
                .edad(4)
                .direccion("Av. 1")
                .ciudad("Cuenca")
                .nombreRaza("Labrador")
                .edadPromedio("12 - 14")
                .pesoPromedio(30.00)
                .alturaPromedio(55.00)
                .build();
        when(mascotaRepo.buscarPorFiltros("Labrador", null, null, null, null, null, null, null))
                .thenReturn(List.of(mock));

        List<Mascota> result = service.buscarPorFiltros("Labrador", null, null, null, null, null, null, null);

        assertEquals(1, result.size());
        assertEquals("Labrador", result.get(0).getNombreRaza());
    }

}
