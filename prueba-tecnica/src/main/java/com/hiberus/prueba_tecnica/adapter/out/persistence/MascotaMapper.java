package com.hiberus.prueba_tecnica.adapter.out.persistence;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface MascotaMapper {

    MascotaDto toDto(Mascota mascota);

    List<MascotaDto> toDtoList(List<Mascota> mascotas);

    Mascota toEntity(MascotaDto mascotaDTO);

    List<Mascota> toEntityList(List<MascotaDto> mascotaDTOs);

}
