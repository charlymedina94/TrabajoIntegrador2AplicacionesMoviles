package com.example.trabajointegrador2aplicacionesmoviles.entidades;

import java.io.Serializable;
import java.util.Date;

public class Momento implements Serializable {

    private int id;
    private String descripcion;
    private byte[] image;
    private String fecha;
    private String ubicacion;
    private String universidad;
    private String urlEncuentro;
    private String lugar;
    private String aula;
    private String fechaEncuentro;
    private String horaEncuentro;
    private String categoria;
    private boolean isActive;



    public Momento(String descripcion, byte[] image, int id, String fecha, String ubicacion, String universidad,
                   String urlEncuentro, String lugar, String aula,String fechaEncuentro, String horaEncuentro, String categoria) {
        this.descripcion = descripcion;
        this.image = image;
        this.id = id;
        this.fecha=fecha;
        this.ubicacion=ubicacion;
        this.universidad = universidad;
        this.urlEncuentro = urlEncuentro;
        this.lugar = lugar;
        this.aula=aula;
        this.fechaEncuentro=fechaEncuentro;
        this.horaEncuentro = horaEncuentro;
        this.categoria=categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getUrlEncuentro() {
        return urlEncuentro;
    }

    public void setUrlEncuentro(String urlEncuentro) {
        this.urlEncuentro = urlEncuentro;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getFechaEncuentro() {
        return fechaEncuentro;
    }

    public void setFechaEncuentro(String fechaEncuentro) {
        this.fechaEncuentro = fechaEncuentro;
    }

    public String getHoraEncuentro() {
        return horaEncuentro;
    }

    public void setHoraEncuentro(String horaEncuentro) {
        this.horaEncuentro = horaEncuentro;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
