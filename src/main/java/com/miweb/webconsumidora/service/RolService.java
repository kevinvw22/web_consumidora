package com.miweb.webconsumidora.service;

import com.miweb.webconsumidora.model.Rol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RolService {

    private final RestTemplate restTemplate;

    @Value("${api.roles.url:http://localhost:8080/api/v1/roles}")
    private String rolesApiUrl;

    public RolService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Rol> getAllRoles() {
        Rol[] roles = restTemplate.getForObject(rolesApiUrl, Rol[].class);
        return Arrays.asList(roles != null ? roles : new Rol[0]);
    }

    public Rol getRolById(Integer id) {
        return restTemplate.getForObject(rolesApiUrl + "/" + id, Rol.class);
    }

    public Rol createRol(Rol rol) {
        return restTemplate.postForObject(rolesApiUrl, rol, Rol.class);
    }

    public void updateRol(Integer id, Rol rol) {
        restTemplate.put(rolesApiUrl + "/" + id, rol);
    }

    public void deleteRol(Integer id) {
        restTemplate.delete(rolesApiUrl + "/" + id);
    }
}