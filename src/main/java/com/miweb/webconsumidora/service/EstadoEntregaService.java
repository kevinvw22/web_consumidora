package com.miweb.webconsumidora.service;

import com.miweb.webconsumidora.model.EstadoEntrega;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class EstadoEntregaService {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public EstadoEntregaService(RestTemplate restTemplate, @Value("${api.estadoentregas.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public List<EstadoEntrega> getAllEstadosEntrega() {
        EstadoEntrega[] estados = restTemplate.getForObject(apiUrl, EstadoEntrega[].class);
        return Arrays.asList(estados != null ? estados : new EstadoEntrega[0]);
    }

    public EstadoEntrega getEstadoEntregaById(Long id) {
        return restTemplate.getForObject(apiUrl + "/" + id, EstadoEntrega.class);
    }

    public EstadoEntrega createEstadoEntrega(EstadoEntrega estadoEntrega) {
        return restTemplate.postForObject(apiUrl, estadoEntrega, EstadoEntrega.class);
    }

    public void updateEstadoEntrega(Long id, EstadoEntrega estadoEntrega) {
        restTemplate.put(apiUrl + "/" + id, estadoEntrega);
    }

    public void deleteEstadoEntrega(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}