package com.example.trabajointegrador2aplicacionesmoviles;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajointegrador2aplicacionesmoviles.entidades.Momento;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MomentoDetail extends AppCompatActivity {

    int position = 0;

    TextView descripcionn;
    TextView fechaa;
    TextView universidadd;
    TextView urlEncuentroo;
    TextView lugarr;
    TextView aulaa;
    TextView fechaEncuentroo;
    TextView horaEncuentroo;
    TextView categoriaa;
    ImageView imgFragment;

    public static ConexionSQLiteHelper sqLiteHelper;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momento_items_fragment);
        sqLiteHelper = new ConexionSQLiteHelper(this, "MomentoDB.sqlite", null, 1);

        descripcionn = (TextView) findViewById(R.id.txtDescripcionFragment);
        fechaa = (TextView) findViewById(R.id.txtFecha);
        imgFragment = (ImageView) findViewById(R.id.imgFragment);
        universidadd = (TextView) findViewById(R.id.txtuniversidad);
        urlEncuentroo = (TextView) findViewById(R.id.txturlEncuentro);
        lugarr = (TextView) findViewById(R.id.txtlugar);
        aulaa = (TextView) findViewById(R.id.txtaula);
        fechaEncuentroo = (TextView) findViewById(R.id.txtfechaEncuentro);
        horaEncuentroo = (TextView) findViewById(R.id.txthoraEncuentro);
        categoriaa = (TextView) findViewById(R.id.txtcategoria);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");


        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM MOMENTO");
            cursor.moveToPosition(position);
            int id = cursor.getInt(0);
            String descripcion = cursor.getString(1);
            imgFragment.setImageBitmap(getImageFromBLOB(cursor.getBlob(cursor.getColumnIndex("image"))));;
            String fecha = cursor.getString(3);
            String universidad = cursor.getString(5);
            String urlEncuentro = cursor.getString(6);
            String lugar = cursor.getString(7);
            String aula = cursor.getString(8);
            String fechaEncuentro = cursor.getString(9);
            String horaEncuentro = cursor.getString(10);
            String categoria = cursor.getString(11);


            descripcionn.setText(descripcion);
            fechaa.setText(fecha);
            universidadd.setText(universidad);
            urlEncuentroo.setText(urlEncuentro);
            lugarr.setText(lugar);
            aulaa.setText(aula);
            fechaEncuentroo.setText(fechaEncuentro);
            horaEncuentroo.setText(horaEncuentro);
            categoriaa.setText(categoria);
        }

    public static Bitmap getImageFromBLOB(byte[] mBlob)
    {
        byte[] bb = mBlob;
        return BitmapFactory.decodeByteArray(bb, 0, bb.length);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "No tenes permisos para acceder a la ubicación del archivo!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    }