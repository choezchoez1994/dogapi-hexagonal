package com.hiberus.prueba_tecnica.adapter.out.dogapi;

import com.hiberus.prueba_tecnica.domain.model.RazaDto;
import com.hiberus.prueba_tecnica.domain.port.out.RazaServicePort;
import com.hiberus.prueba_tecnica.domain.usecase.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DogApiClient implements RazaServicePort {
    private static final Logger log = LoggerFactory.getLogger(DogApiClient.class);

    private final RestService restService;

    @Value("${dogapi.url}")
    private String apiUrl;
    @Value("${dogapi.key}")
    private String apiKey;

    @Override
    @Cacheable(value = "razas", key = "#nombreRaza")
    public RazaDto obtenerRazaAPI(String nombreRaza) {
        Map<String, String> headers = Map.of("x-api-key", apiKey);
        List<RazaDto> razaDtoList = restService.restGETList(apiUrl + "/v1/breeds/search?q=" + nombreRaza, RazaDto.class, headers);
        log.info("Respuesta de la API: {}", razaDtoList.size());
        RazaDto razaDto = null;
        if (razaDtoList != null && !razaDtoList.isEmpty() && razaDtoList.get(0) != null) {
            razaDto = razaDtoList.get(0);
            razaDto.setPromedioAltura(this.calcularPromedio(razaDto.getHeight().getMetric()));
            razaDto.setPromedioPeso(this.calcularPromedio(razaDto.getWeight().getMetric()));
        } else {
            throw new IllegalArgumentException("Hay múltiples razas con el nombre " + nombreRaza + ". Por favor, especifica más detalles.");
        }
        return razaDto;
    }


    public double calcularPromedio(String rango) {
        String[] partes = rango.split(" - ");
        double minimo = Double.parseDouble(partes[0].trim());
        double maximo = Double.parseDouble(partes[1].trim());
        return (minimo + maximo) / 2.0;
    }
}
