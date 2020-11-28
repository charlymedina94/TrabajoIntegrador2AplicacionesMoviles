package com.example.trabajointegrador2aplicacionesmoviles;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.trabajointegrador2aplicacionesmoviles.entidades.Momento;
import com.example.trabajointegrador2aplicacionesmoviles.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //crea tabla usuario
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);

        onCreate(db);
    }


    //Chequeo de login

    public Boolean dniPassword (String dni, String password){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where id=? and password=?", new String[]{dni,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }



    //########### AGREGO MOMENTOS

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }



    public void insertData(String descripcion, byte[] image, String fecha, String ubicacion, String universidad,
                           String urlEncuentro, String lugar, String aula,String fechaEncuentro, String horaEncuentro,
                           String categoria){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MOMENTO VALUES (NULL, ?,?,?,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, descripcion);
        statement.bindBlob(2, image);
        statement.bindString(3, fecha);
        statement.bindString(4, ubicacion);
        statement.bindString(5, universidad);
        statement.bindString(6, urlEncuentro);
        statement.bindString(7, lugar);
        statement.bindString(8, aula);
        statement.bindString(9, fechaEncuentro);
        statement.bindString(10, horaEncuentro);
        statement.bindString(11, categoria);


        statement.executeInsert();
    }




    public void updateData(String descripcion,  int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE MOMENTO SET descripcion = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, descripcion);
        statement.bindDouble(2, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM MOMENTO WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

}