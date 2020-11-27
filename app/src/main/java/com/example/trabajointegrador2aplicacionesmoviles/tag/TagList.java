/*

package com.example.trabajointegrador2aplicacionesmoviles.tag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajointegrador2aplicacionesmoviles.MomentoListAdapter;
import com.example.trabajointegrador2aplicacionesmoviles.R;
import com.example.trabajointegrador2aplicacionesmoviles.SubirMomentoActivity;
import com.example.trabajointegrador2aplicacionesmoviles.entidades.Momento;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class TagList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Momento> list;
    TagListAdapter adapter = null;


    EditText search;

    EditText tagText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_list_activity);

        gridView = (GridView) findViewById(R.id.gridViewTag);
        list = new ArrayList<>();
        adapter = new TagListAdapter(this, R.layout.tag_items, list);
        gridView.setAdapter(adapter);



        LOGICA DE AGARRAR EL VALOR INGRESADO COMO TAG Y BUSCARLO


      //  Dialog dialog = new Dialog(TagList.this);
        dialog.setContentView(R.layout.tag_activity);
        search = (EditText) dialog.findViewById(R.id.buscarTag);





        String holis = search.getText().toString().trim();



        Toast.makeText(TagList.this, "va "+holis+". fue", Toast.LENGTH_SHORT).show();

        System.out.println("searchhhh=================================================sdadasdasdsad========== "+holis);



        // get all data from sqlite

        String holis = "#Pepito";

        Cursor cursor = BuscarTagActivity.sqLiteHelper.getData("SELECT * FROM MOMENTO WHERE descripcion LIKE '%"+holis+"%'");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String descripcion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String fecha=cursor.getString(3);
            String ubicacion=cursor.getString(4);
            String universidad=cursor.getString(5);
            String urlEncuentro=cursor.getString(6);
            String lugar=cursor.getString(7);
            String aula=cursor.getString(8);
            String fechaEncuentro=cursor.getString(9);
            String horaEncuentro=cursor.getString(10);
            String categoria=cursor.getString(11);

            list.add(new Momento(descripcion, image, id,fecha,ubicacion, universidad, urlEncuentro, lugar, aula, fechaEncuentro, horaEncuentro, categoria));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Modificar", "Eliminar"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(TagList.this);

                dialog.setTitle("Elige una opción");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = SubirMomentoActivity.sqLiteHelper.getData("SELECT id FROM MOMENTO");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(TagList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = SubirMomentoActivity.sqLiteHelper.getData("SELECT id FROM MOMENTO");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    //ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_tag_activity);
        dialog.setTitle("Update");

        //imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtDescripcion = (EditText) dialog.findViewById(R.id.edtDescripcion);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        //imageViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        MomentoList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubirMomentoActivity.sqLiteHelper.updateData(
                            edtDescripcion.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Actualizado correctamente!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Error al actualizar", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int idMomento){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(TagList.this);

        dialogDelete.setTitle("Espera!!");
        dialogDelete.setMessage("Estas seguro que deseas eliminar este momento?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    SubirMomentoActivity.sqLiteHelper.deleteData(idMomento);
                    Toast.makeText(getApplicationContext(), "Borrado correctamente!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = SubirMomentoActivity.sqLiteHelper.getData("SELECT * FROM MOMENTO");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String descripcion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String fecha=cursor.getString(3);
            String ubicacion=cursor.getString(4);
            String universidad=cursor.getString(5);
            String urlEncuentro=cursor.getString(6);
            String lugar=cursor.getString(7);
            String aula=cursor.getString(8);
            String fechaEncuentro=cursor.getString(9);
            String horaEncuentro=cursor.getString(10);
            String categoria=cursor.getString(11);

            list.add(new Momento(descripcion, image, id,fecha,ubicacion, universidad, urlEncuentro, lugar, aula, fechaEncuentro, horaEncuentro, categoria));
        }
        adapter.notifyDataSetChanged();
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
*/