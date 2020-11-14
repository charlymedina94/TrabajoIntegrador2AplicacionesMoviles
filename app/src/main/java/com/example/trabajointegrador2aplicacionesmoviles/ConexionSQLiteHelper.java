package com.example.trabajointegrador2aplicacionesmoviles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.trabajointegrador2aplicacionesmoviles.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //crea tabla usuario
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        //db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        //db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);
        onCreate(db);
    }

}
