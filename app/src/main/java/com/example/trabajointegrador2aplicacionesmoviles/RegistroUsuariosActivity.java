package com.example.trabajointegrador2aplicacionesmoviles;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajointegrador2aplicacionesmoviles.utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {

    EditText campoId,campoPassword,campoNombre,campoCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoId= (EditText) findViewById(R.id.campoId);
        campoPassword=(EditText)findViewById(R.id.campoPassword);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoCorreo= (EditText) findViewById(R.id.campoCorreo);

    }

    public void onClick(View view) {
        registrarUsuarios();
        validateEmailAddress(campoCorreo);
        //registrarUsuariosSql();
    }

    private boolean validateEmailAddress(EditText campoCorreo) {
        String emailInput= campoCorreo.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this,"Email validado",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(this,"Email invalido",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /* ### REGISTRO DE USUARIOS POR QUERY
    private void registrarUsuariosSql() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')
        String insert="INSERT INTO "+ Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoTelefono.getText().toString()+"')";
        db.execSQL(insert);
        db.close();
    }
     */


    private void registrarUsuarios() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD,campoPassword.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,campoCorreo.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}