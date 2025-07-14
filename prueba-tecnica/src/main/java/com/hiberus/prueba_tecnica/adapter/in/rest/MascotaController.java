package com.hiberus.prueba_tecnica.adapter.in.rest;

import com.hiberus.prueba_tecnica.adapter.out.persistence.entity.Mascota;
import com.hiberus.prueba_tecnica.domain.model.MascotaDto;
import com.hiberus.prueba_tecnica.domain.port.in.RegistrarMascotaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final RegistrarMascotaUseCase registrarMascotaUseCase;

    @PostMapping("/registrar")
    public ResponseEntity<MascotaDto> registrar(@RequestBody MascotaDto data) {
        return ResponseEntity.ok(registrarMascotaUseCase.registrarMascota(data));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Mascota>> buscarPorFiltros(
            @RequestParam(required = false) String raza,
            @RequestParam(required = false) String grupo,
            @RequestParam(required = false) Integer edadDesde,
            @RequestParam(required = false) Integer edadHasta,
            @RequestParam(required = false) Integer pesoDesde,
            @RequestParam(required = false) Integer pesoHasta,
            @RequestParam(required = false) Integer alturaDesde,
            @RequestParam(required = false) Integer alturaHasta
    ) {
        List<Mascota> resultado = registrarMascotaUseCase.buscarPorFiltros(
                raza, grupo, edadDesde, edadHasta,
                pesoDesde, pesoHasta, alturaDesde, alturaHasta
        );
        return ResponseEntity.ok(resultado);
    }

}
