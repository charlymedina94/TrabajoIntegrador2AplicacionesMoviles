package com.example.trabajointegrador2aplicacionesmoviles.entidades;

public class Momento {

    private int id;
    private String descripcion;
    private byte[] image;

    public Momento(String descripcion, byte[] image, int id) {
        this.descripcion = descripcion;
        this.image = image;
        this.id = id;
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
