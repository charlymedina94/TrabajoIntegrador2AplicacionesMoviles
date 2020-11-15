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

    public static final String CREAR_TABLA_USUARIO=" CREATE TABLE " + ""+ TABLA_USUARIO +" ("+ CAMPO_ID +" " + " INTEGER, "+ CAMPO_PASSWORD +" TEXT, "+ CAMPO_NOMBRE +" TEXT,"+ CAMPO_CORREO +" TEXT, "+CAMPO_ROLES +" TEXT, "+ CAMPO_ISACTIVE +" TEXT )";

    //Constantes campos tabla mascota
    public static final String TABLA_MASCOTA="mascota";
    public static final String CAMPO_ID_MASCOTA="id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre_mascota";
    public static final String CAMPO_RAZA_MASCOTA="raza_mascota";
    public static final String CAMPO_ID_DUENIO="id_duenio";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENIO+" INTEGER)";

}
