package com.example.trabajointegrador2aplicacionesmoviles.entidades;

import java.util.Date;

public class Momento {

    private int id;
    private String descripcion;
    private byte[] image;
    private String fecha;
    private String ubicacion;


    public Momento(String descripcion, byte[] image, int id, String fecha, String ubicacion) {
        this.descripcion = descripcion;
        this.image = image;
        this.id = id;
        this.fecha=fecha;
        this.ubicacion=ubicacion;
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


}
