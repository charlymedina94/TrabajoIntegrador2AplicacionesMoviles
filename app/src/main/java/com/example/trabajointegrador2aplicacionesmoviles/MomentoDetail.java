package com.example.trabajointegrador2aplicacionesmoviles;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.ArrayList;


public class MomentoDetail extends AppCompatActivity {

    int position = 0;

    ListView gridView;
    Momento list;
    MomentoListAdapter adapter = null;

    TextView descripcionn;
    TextView fechaa;
    TextView ubicacionn;

    ImageView imgFragment;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momento_items_fragment);

        descripcionn = (TextView) findViewById(R.id.txtDescripcionFragment);
        fechaa = (TextView) findViewById(R.id.txtFecha);
        ubicacionn = (TextView) findViewById(R.id.txtUbicacion);
        imgFragment = (ImageView) findViewById(R.id.imgFragment);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");


        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM MOMENTO");// ORDER BY id DESC");
            cursor.moveToPosition(position);
            int id = cursor.getInt(0);
            String descripcion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String fecha = cursor.getString(3);
            String ubicacion = cursor.getString(4);

            descripcionn.setText(descripcion);
            fechaa.setText(fecha);
            ubicacionn.setText(ubicacion);
            //imgFragment.setImageBitmap(image);

            //new Momento(descripcion, image, id, fecha, ubicacion);



        }

    }