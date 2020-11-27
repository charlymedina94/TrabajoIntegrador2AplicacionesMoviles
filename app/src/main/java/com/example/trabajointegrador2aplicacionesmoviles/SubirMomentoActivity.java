package com.example.trabajointegrador2aplicacionesmoviles;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.KeyEvent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubirMomentoActivity extends AppCompatActivity {

    EditText edtDescripcion;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;
    private final String CARPETA_RAIZ = "misImagenes/";
    private final String RUTA_IMAGEN = CARPETA_RAIZ + "misFotos";
    String path;

    EditText universidad;
    EditText urlEncuentro;
    EditText lugar;
    EditText aula;
    TextView fechaEncuentro;
    TextView horaEncuentro;
    EditText categoria;

    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_CODE_FOTO = 998;

    public static ConexionSQLiteHelper sqLiteHelper;


    private Location location;
    private LocationManager locationManager = null;
    private MyLocationListener locationListener = null;
    private double latitude;
    private double longitude;


    private Boolean flag = false;
    String ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_momento);

        checkLocationPermission();


        init();

        sqLiteHelper = new ConexionSQLiteHelper(this, "MomentoDB.sqlite", null, 1);

       //sqLiteHelper.queryData("DROP TABLE IF EXISTS MOMENTO");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MOMENTO(Id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion VARCHAR, image BLOB, fecha VARCHAR, ubicacion VARCHAR ,universidad VARCHAR, urlEncuentro VARCHAR, lugar VARCHAR, aula VARCHAR, fechaEncuentro VARCHAR, horaEncuentro VARCHAR, categoria VARCHAR)");

        //......................................................................................
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //......................................................................................

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLocationPermission();
                ActivityCompat.requestPermissions(
                        SubirMomentoActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                try {
                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                    String fecha = dateFormat.format(date);
                    String ubicacion = "";
                    /*
                    String universidad = "Unaj";
                    String urlEncuentro;
                    String lugar;
                    String aula;
                    String fechaEncuentro;
                    String horaEncuentro;
                    String categoria;
                    */

                    //..................................  UBICACION ....................................................

                    flag = displayGpsStatus();
                    if (flag) {

                        // Instancio el listener
                        locationListener = new MyLocationListener();

                        // Recupero las coordenadas



                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
                        }


                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);



                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                ubicacion = String.valueOf(latitude) +" / " + String.valueOf(longitude);

                                System.out.println("latitud: "+latitude +" - longitud: "+ longitude);
                                System.out.println(ubicacion);
                            }

                        }


                    } else {
                        Toast.makeText(
                                getBaseContext(),
                                "El GPS se encuentra deshabilitado", Toast.LENGTH_SHORT)
                                .show();
                    }
                    //......................................................................................





                    sqLiteHelper.insertData(
                            edtDescripcion.getText().toString().trim(),
                            imageViewToByte(imageView),
                            fecha,
                            ubicacion,
                            universidad.getText().toString().trim(),
                            urlEncuentro.getText().toString().trim(),
                            lugar.getText().toString().trim(),
                            aula.getText().toString().trim(),
                            fechaEncuentro.getText().toString().trim(),
                            horaEncuentro.getText().toString().trim(),
                            categoria.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Agregado correctamente!", Toast.LENGTH_SHORT).show();
                    edtDescripcion.setText("");
                    universidad.setText("");
                    urlEncuentro.setText("");
                    lugar.setText("");
                    aula.setText("");
                    fechaEncuentro.setText("");
                    horaEncuentro.setText("");
                    categoria.setText("");

                    imageView.setImageResource(R.drawable.icono_background);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No se pudo subir el momento!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



        Button buttonFechaEncuentro = (Button) findViewById(R.id.buttonFechaEncuentro);
        buttonFechaEncuentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c= Calendar.getInstance();
                int dia=c.get(Calendar.DAY_OF_MONTH);
                int mes=c.get(Calendar.MONTH);
                int ano=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SubirMomentoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fechaEncuentro.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,ano,mes,dia);
                datePickerDialog.show();


            }
        });



        Button buttonHoraEncuentro = (Button) findViewById(R.id.buttonHoraEncuentro);
        buttonHoraEncuentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c= Calendar.getInstance();
                int hora=c.get(Calendar.HOUR_OF_DAY);
                int minutos=c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SubirMomentoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaEncuentro.setText(hourOfDay+":"+minute);
                    }
                },hora,minutos,false);
                timePickerDialog.show();


            }
        });



/*
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubirMomentoActivity.this, MomentoList.class);
                startActivity(intent);
            }
        });

 */
    }

    //......................................................................................

    /*---- Método que chequea si está o no habilitado el GPS ----- */
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
                contentResolver, LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }


    //FECHA
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.fechaEncuentro);
        textView.setText(currentDateString);
    }



    /*---- Listener que obtiene las coordenadas ---- */
    private class MyLocationListener implements LocationListener {

        private double longitude, latitude;

        @Override
        public void onLocationChanged(Location loc) {
            this.longitude = loc.getLongitude();
            this.latitude = loc.getLatitude();

            // Muestro las coordenadas
            ubicacion = this.latitude + "\n\n" + this.longitude;


        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

    //......................................................................................



    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }












    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(SubirMomentoActivity.this);
        alertOpciones.setTitle("Seleccione una opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")) {
                    tomarFoto();

                } else {
                    if (opciones[i].equals("Cargar Imagen")) {
                        if (requestCode == REQUEST_CODE_GALLERY) {
                            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                            } else {
                                Toast.makeText(getApplicationContext(), "No tenes permisos para acceder a la dirección de este archivo!", Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                    } else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void tomarFoto() {
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();
        String nombreImagen = "";
        if (isCreada == false) {
            isCreada = fileImagen.mkdirs();
        }
        if (isCreada == true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".png";
        }
        path = Environment.getExternalStorageDirectory() + File.separator + RUTA_IMAGEN + File.separator + nombreImagen;

        File imagen = new File(path);
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authorities = getApplicationContext().getPackageName() + ".provider";
            Uri imageUri = FileProvider.getUriForFile(this, authorities, imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }

        startActivityForResult(intent, REQUEST_CODE_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQUEST_CODE_GALLERY:
                    Uri uri = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_CODE_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path:" + path);
                        }
                    });
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);
                    break;
            }


        }

//        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                imageView.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            Intent intent = new Intent(SubirMomentoActivity.this, MomentoList.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    private void init() {
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        //btnList = (Button) findViewById(R.id.btnList);
        imageView = (ImageView) findViewById(R.id.imageView);
        universidad = (EditText) findViewById(R.id.universidad);
        urlEncuentro = (EditText) findViewById(R.id.urlEncuentro);
        lugar  = (EditText) findViewById(R.id.lugar);
        aula  = (EditText) findViewById(R.id.aula);
        fechaEncuentro  = (TextView) findViewById(R.id.fechaEncuentro);
        horaEncuentro  = (TextView) findViewById(R.id.horaEncuentro);
        categoria  = (EditText) findViewById(R.id.categoria);
    }

}