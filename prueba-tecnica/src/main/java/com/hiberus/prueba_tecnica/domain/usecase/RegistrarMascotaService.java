package com.hiberus.prueba_tecnica.domain.usecase;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import com.hiberus.prueba_tecnica.domain.model.RazaDto;
import com.hiberus.prueba_tecnica.domain.port.in.RegistrarMascotaUseCase;
import com.hiberus.prueba_tecnica.domain.port.out.MascotaRepositoryPort;
import com.hiberus.prueba_tecnica.domain.port.out.RazaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrarMascotaService implements RegistrarMascotaUseCase {

    private final MascotaRepositoryPort repo;
    private final RazaServicePort razaService;

    @Override
    public MascotaDto registrarMascota(MascotaDto mascotaDto) {
        RazaDto datosRaza = razaService.obtenerRazaAPI(mascotaDto.getNombreRaza());
        System.out.println("Datos de raza obtenidos: " + datosRaza.getName() +
                " - Grupo: " + datosRaza.getBreedGroup() +
                " - Tamaño: " + datosRaza.getWeight() +
                " - Esperanza de vida: " + datosRaza.getLifeSpan());
        mascotaDto.setRazaDto(datosRaza);
        return repo.guardar(mascotaDto);
    }

    @Override
    public List<Mascota> buscarPorFiltros(String raza, String grupo, Integer edadDesde, Integer edadHasta,
                                          Integer pesoDesde, Integer pesoHasta, Integer alturaDesde, Integer alturaHasta) {
        return repo.buscarPorFiltros(raza, grupo, edadDesde, edadHasta, pesoDesde, pesoHasta, alturaDesde, alturaHasta);
    }
}
