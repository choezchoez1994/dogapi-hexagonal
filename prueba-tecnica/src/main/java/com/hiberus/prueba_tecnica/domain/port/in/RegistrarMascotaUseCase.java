package com.hiberus.prueba_tecnica.domain.port.in;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;

import java.util.List;

public interface RegistrarMascotaUseCase {

    MascotaDto registrarMascota(MascotaDto mascotaDto);

    List<Mascota> buscarPorFiltros(String raza, String grupo, Integer edadDesde, Integer edadHasta,
                                   Integer pesoDesde, Integer pesoHasta, Integer alturaDesde, Integer alturaHasta);
}
