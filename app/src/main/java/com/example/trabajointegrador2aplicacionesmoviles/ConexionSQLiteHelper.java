package com.example.trabajointegrador2aplicacionesmoviles;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
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


    //Chequeo de login

    public Boolean dniPassword (String dni, String password){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where id=? and password=?", new String[]{dni,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


}


//    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException{
//        Cursor mcursor=null;
//
//        mcursor=this.getReadableDatabase().query(Utilidades.TABLA_USUARIO,
//                new String[]{Utilidades.CAMPO_ID,Utilidades.CAMPO_PASSWORD,Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_CORREO};
//
//
//        return mcursor;
//    }
//}


