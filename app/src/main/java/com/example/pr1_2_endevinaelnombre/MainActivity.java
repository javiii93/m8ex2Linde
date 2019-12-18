package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Jugador> players = new ArrayList<>();
    Button checkButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Button exit;
    Button rankingButton;
    TextView textView1;
    EditText usernumber;
    int numberToSolve = new Random().nextInt(100);
    String comentario, nickname;
    int contadorIntentos, nombre;
    ImageView imageView;
    Jugador j1 = new Jugador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkButton = findViewById(R.id.button);
        rankingButton = findViewById(R.id.button2);
        exit = findViewById(R.id.button3);
        usernumber = findViewById(R.id.editText);
        textView1 = findViewById(R.id.textView4);
        textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = Integer.parseInt(usernumber.getText().toString());
                contadorIntentos++;
                if (nombre < numberToSolve) {

                    comentario = "El numero que buscas es mayor";
                    textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                } else if (nombre > numberToSolve) {
                    comentario = "El numero que buscas es mas pequeño";
                    textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                } else {
                    textView1.setText("Congratulations, has adivinado el numero oculto, era el: " + numberToSolve + " Tu puntacion ha sido de " + contadorIntentos + " puntos, si quieres empezar de nuevo sigue escribiendo numeros");
                    comentario = "Lo has 'clavao' papi";
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                    dialogo();
                }
                usernumber.setText("");
            }
        });

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Ranking2Activity.class);
                startActivity(intent);
            }
        });
exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Ranking2Activity ra=new Ranking2Activity();
        // ra.salida();

       salida();
    }
});
            }
public void salida(){
        finish();
}


public void eliminarRepetidos(){

    Set<Jugador> hs = new HashSet<>(players);
    players.clear();
    players.addAll(hs);

}
    public void dialogo() {
        final String menssage = "Escriba su apodo para guardar su puntuacion, si no quiere pulse cancelar";

        final AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage(menssage);

        builder.setTitle("Hello makina, tu puntuacion es de " + contadorIntentos);

        builder.setCancelable(false);
        final EditText input = new EditText(this);
        builder.setView(input);


        builder
                .setPositiveButton(
                        "Añadir",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                nickname = input.getText().toString();
                                int numm = contadorIntentos;
                                j1 = new Jugador(nickname, numm);

                                contadorIntentos = 0;
                                numberToSolve = new Random().nextInt(100);
                                if(j1.getBitmap()==null){
                                    dispatchTakePictureIntent();
                                }
                                players.add(j1);

                            }
                        });

        builder
                .setNegativeButton(
                        "Cancelar",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                finish();
                            }
                        });


        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void guardarFoto(Jugador j){
        Bitmap bmp;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp =  j.getBitmap();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte array[] = baos.toByteArray();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            j1.setBitmap(imageBitmap);
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        j1.setCurrentPhotoPath(image.getAbsolutePath());
        return image;
    }
}


