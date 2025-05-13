package com.miweb.webconsumidora.service;

import com.miweb.webconsumidora.model.Distrito;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;

@Service
public class DistritoService {

    private final RestTemplate restTemplate;
    private final String apiUrlBase;

    public DistritoService(RestTemplate restTemplate, @Value("${api.distritos.url:http://localhost:8080/api/v1/distritos}") String apiUrlBase) {
        this.restTemplate = restTemplate;
        this.apiUrlBase = apiUrlBase;
    }

    public List<Distrito> getAllDistritos() {
        ResponseEntity<Distrito[]> response = restTemplate.getForEntity(apiUrlBase, Distrito[].class);
        return Arrays.asList(response.getBody());
    }

    public Distrito getDistritoById(Long id) {
        return restTemplate.getForObject(apiUrlBase + "/" + id, Distrito.class);
    }

    public Distrito createDistrito(Distrito distrito) {
        return restTemplate.postForObject(apiUrlBase, distrito, Distrito.class);
    }

    public void updateDistrito(Long id, Distrito distrito) {
        restTemplate.put(apiUrlBase + "/" + id, distrito);
    }

    public void deleteDistrito(Long id) {
        restTemplate.delete(apiUrlBase + "/" + id);
    }
}