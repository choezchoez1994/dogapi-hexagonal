package com.hiberus.prueba_tecnica.adapter.out.persistence;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.adapter.out.persistence.repository.MascotaRepository;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import com.hiberus.prueba_tecnica.domain.port.out.MascotaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MascotaRepositoryAdapter implements MascotaRepositoryPort {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;

    @Override
    public MascotaDto guardar(MascotaDto mascotaDto) {
        Mascota mascota = mascotaMapper.toEntity(mascotaDto);
        mascota.setGrupo(mascotaDto.getRazaDto().getBreedGroup());
        mascota.setEdadPromedio(mascotaDto.getRazaDto().getLifeSpan());
        mascota.setPesoPromedio(mascotaDto.getRazaDto().getPromedioPeso());
        mascota.setAlturaPromedio(mascotaDto.getRazaDto().getPromedioAltura());
        mascota.setTemperamento(mascotaDto.getRazaDto().getTemperament());
        mascota.setProposito(mascotaDto.getRazaDto().getBredFor());
        mascota = mascotaRepository.save(mascota);
        System.out.println("ID: " + mascota.getId() + "  Guardando mascota: " + mascota.getNombre());
        return mascotaMapper.toDto(mascota);
    }

    @Override
    public List<Mascota> buscarPorFiltros(String raza, String grupo, Integer edadDesde, Integer edadHasta, Integer pesoDesde, Integer pesoHasta, Integer alturaDesde, Integer alturaHasta) {
        return mascotaRepository.findAll().stream()
                .filter(m -> raza == null || m.getNombreRaza().equalsIgnoreCase(raza))
                .filter(m -> grupo == null || (m.getGrupo() != null && m.getGrupo().equalsIgnoreCase(grupo)))
                .filter(m -> edadDesde == null || m.getEdad() >= edadDesde)
                .filter(m -> edadHasta == null || m.getEdad() <= edadHasta)
                .filter(m -> pesoDesde == null || m.getPesoPromedio() >= pesoDesde)
                .filter(m -> pesoHasta == null || m.getPesoPromedio() <= pesoHasta)
                .filter(m -> alturaDesde == null || m.getAlturaPromedio() >= alturaDesde)
                .filter(m -> alturaHasta == null || m.getAlturaPromedio() <= alturaHasta)
                .collect(Collectors.toList());
    }
}
