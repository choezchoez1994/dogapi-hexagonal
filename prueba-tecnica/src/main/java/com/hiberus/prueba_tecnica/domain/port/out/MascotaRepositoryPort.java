package com.hiberus.prueba_tecnica.domain.port.out;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;

import java.util.List;

public interface MascotaRepositoryPort {

    MascotaDto guardar(MascotaDto mascotaDto);

    List<Mascota> buscarPorFiltros(String raza, String grupo, Integer edadDesde, Integer edadHasta,
                                   Integer pesoDesde, Integer pesoHasta, Integer alturaDesde, Integer alturaHasta);
}
