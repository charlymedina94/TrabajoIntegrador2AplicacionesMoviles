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

    ListView gridView;
    Momento list;
    MomentoListAdapter adapter = null;

    TextView descripcionn;
    TextView fechaa;
    TextView ubicacionn;

    ImageView imgFragment;
    public static ConexionSQLiteHelper sqLiteHelper;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momento_items_fragment);
        sqLiteHelper = new ConexionSQLiteHelper(this, "MomentoDB.sqlite", null, 1);

        descripcionn = (TextView) findViewById(R.id.txtDescripcionFragment);
        fechaa = (TextView) findViewById(R.id.txtFecha);
        ubicacionn = (TextView) findViewById(R.id.txtUbicacion);
        imgFragment = (ImageView) findViewById(R.id.imgFragment);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");


        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM MOMENTO ORDER BY id DESC");
            cursor.moveToPosition(position);
            int id = cursor.getInt(0);
            String descripcion = cursor.getString(1);
//            byte[] image = cursor.getBlob(2);
            imgFragment.setImageBitmap(getImageFromBLOB(cursor.getBlob(cursor.getColumnIndex("image"))));;
            String fecha = cursor.getString(3);
            String ubicacion = cursor.getString(4);

            descripcionn.setText(descripcion);
            fechaa.setText(fecha);
            ubicacionn.setText(ubicacion);
            //imgFragment.setImageBitmap(image);
            //new Momento(descripcion, image, id, fecha, ubicacion);



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
                //imageViewFood.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    }