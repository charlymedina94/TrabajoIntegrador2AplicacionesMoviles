package com.example.trabajointegrador2aplicacionesmoviles.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;
    private String password;
    private String nombre;
    private String correo;
    private String roles;
    public boolean isActive;

    public Usuario(Integer id, String password, String nombre, String correo, String roles, boolean isActive) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.roles = roles;
        this.isActive=isActive;

    }

    public Usuario(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
