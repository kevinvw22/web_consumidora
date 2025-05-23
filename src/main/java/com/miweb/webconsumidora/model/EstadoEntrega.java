package com.miweb.webconsumidora.model;

public class EstadoEntrega {
    private Long id;
    private String descripcion;

    public EstadoEntrega() {
    }

    public EstadoEntrega(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoEntrega{" +
                "id=" + id +
                ", descripcion='" + descripcion + "'" +
                '}';
    }
}