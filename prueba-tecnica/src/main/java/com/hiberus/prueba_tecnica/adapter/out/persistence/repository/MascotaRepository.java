package com.hiberus.prueba_tecnica.adapter.out.persistence.repository;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
