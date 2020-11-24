package com.example.trabajointegrador2aplicacionesmoviles;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MostrarFeedActivity extends AppCompatActivity {

    EditText edtDescripcion;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static ConexionSQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momento_list_activity);

        init();

        sqLiteHelper = new ConexionSQLiteHelper(this, "MomentoDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MOMENTO(Id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion VARCHAR, image BLOB, fecha VARCHAR, ubicacion VARCHAR)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MostrarFeedActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String fecha="";
                    String ubicacion = "";
                    sqLiteHelper.insertData(
                            edtDescripcion.getText().toString().trim(),
                            imageViewToByte(imageView),fecha,ubicacion
                    );
                    Toast.makeText(getApplicationContext(), "Agregado correctamente!", Toast.LENGTH_SHORT).show();
                    edtDescripcion.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se pudo subir el momento!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        /*
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarFeedActivity.this, MomentoList.class);
                startActivity(intent);
            }
        });

         */
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "No tenes permisos para acceder a la dirección de este archivo!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        //btnList = (Button) findViewById(R.id.btnList);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


}