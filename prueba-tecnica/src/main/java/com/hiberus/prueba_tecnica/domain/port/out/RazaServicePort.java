package com.hiberus.prueba_tecnica.domain.port.out;

import com.hiberus.prueba_tecnica.domain.model.RazaDto;

import java.util.List;

public interface RazaServicePort {
    RazaDto obtenerRazaAPI(String nombreRaza);
}
