package com.example.trabajointegrador2aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.trabajointegrador2aplicacionesmoviles.ConexionSQLiteHelper;
import com.example.trabajointegrador2aplicacionesmoviles.tag.BuscarTagActivity;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1, b2;
//    ConexionSQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.campoId2);
        e2=(EditText)findViewById(R.id.campoPassword2);
        b1=(Button) findViewById(R.id.btnLogin);

        cargarPreferencias();

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni =e1.getText().toString();
                String pass=e2.getText().toString();
                Boolean chkdnipassword= conn.dniPassword(dni,pass);
                if (chkdnipassword==true){
                    Toast.makeText(getApplicationContext(),"Logueo exitoso",Toast.LENGTH_SHORT).show();
                    guardarPreferencias();
                }else {
                    Toast.makeText(getApplicationContext(),"DNI y/o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = preferences.getString("user","");
        String pass = preferences.getString("pass","");

        e1.setText(user);
        e2.setText(pass);


    }

    private void guardarPreferencias() {
        SharedPreferences preferences= getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario =e1.getText().toString();
        String pass=e2.getText().toString();

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user",usuario);
        editor.putString("pass",pass);
        editor.commit();
    }


    public void onClick(View v) {



        Intent miIntent=null;
        switch (v.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,RegistroUsuariosActivity.class);
                break;





            case R.id.btnConsultaIndividual:
                miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                break;



            case R.id.btnSubirMomento:
                miIntent=new Intent(MainActivity.this,SubirMomentoActivity.class);
                break;

            case R.id.btnBuscarTag:
                miIntent=new Intent(MainActivity.this, BuscarTagActivity.class);
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