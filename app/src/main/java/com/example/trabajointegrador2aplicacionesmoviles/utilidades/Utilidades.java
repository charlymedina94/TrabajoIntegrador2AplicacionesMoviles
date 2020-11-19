package com.example.trabajointegrador2aplicacionesmoviles.utilidades;

import java.lang.reflect.Array;

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_PASSWORD= "password";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_ROLES="roles";
    public static final String CAMPO_ISACTIVE="isActive";

    public static final String CREAR_TABLA_USUARIO=" CREATE TABLE " + ""+ TABLA_USUARIO +" ("+ CAMPO_ID +" " + " INTEGER PRIMARY KEY UNIQUE, "+ CAMPO_PASSWORD +" TEXT, "+ CAMPO_NOMBRE +" TEXT,"+ CAMPO_CORREO +" TEXT UNIQUE, "+CAMPO_ROLES +" TEXT, "+ CAMPO_ISACTIVE +" TEXT )";



}
