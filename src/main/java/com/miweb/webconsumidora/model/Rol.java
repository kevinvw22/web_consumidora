package com.miweb.webconsumidora.model;

public class Rol {
    private Integer id;
    private String rolName;

    public Rol() {
    }

    public Rol(Integer id, String rolName) {
        this.id = id;
        this.rolName = rolName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", rolName='" + rolName + '\'' +
                '}';
    }
}