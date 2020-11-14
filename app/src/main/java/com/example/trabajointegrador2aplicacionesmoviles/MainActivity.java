package com.example.trabajointegrador2aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    public void onClick(View v) {
        Intent miIntent=null;
        switch (v.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,RegistroUsuariosActivity.class);
                break;

                /*

            case R.id.btnRegistroMascota:
                miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);
                break;

                */



            case R.id.btnConsultaIndividual:
                miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                break;

                /*

                case R.id.btnConsultaSpinner:
                miIntent=new Intent(MainActivity.this,ConsultaComboActivity.class);
                break;
            case R.id.btnConsultaLista:
                miIntent=new Intent(MainActivity.this,ConsultarListaListViewActivity.class);
                break;
            case R.id.btnConsultaListaMascota:
                miIntent=new Intent(MainActivity.this,ListaMascotasActivity.class);
                break;
            case R.id.btnConsultaListaPersonasRecycler:
                miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
                break;


                 */
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
}