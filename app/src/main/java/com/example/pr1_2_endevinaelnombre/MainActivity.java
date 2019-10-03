package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
     Button checkButton;
    Button rankingButton;
    TextView textView1;
    EditText usernumber;
    final int numberToSolve=new Random().nextInt(100);
    String comentario;
    int contadorIntentos=0,nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkButton= findViewById(R.id.button);
        rankingButton=findViewById(R.id.button2);
        usernumber = findViewById(R.id.editText);
        textView1= findViewById(R.id.textView4);
        textView1.setText("Numero Intents: "+contadorIntentos+" Numero a adivinar: "+numberToSolve);
             checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=Integer.parseInt(usernumber.getText().toString());
                contadorIntentos++;
                if(nombre<numberToSolve){
                    comentario="El numero que buscas es mayor";
                    textView1.setText("Numero Intents: "+contadorIntentos+" Numero a adivinar: "+numberToSolve);
                    Toast.makeText(getApplicationContext(),comentario, Toast.LENGTH_LONG).show();
                }else if(nombre>numberToSolve){
                    comentario="El numero que buscas es mas pequeño";
                    textView1.setText("Numero Intents: "+contadorIntentos+" Numero a adivinar: "+numberToSolve);
                Toast.makeText(getApplicationContext(),comentario, Toast.LENGTH_LONG).show();
            }else{
                    textView1.setText("Congratulations, has adivinado el numero oculto, era el: "+numberToSolve+" Tu puntacion ha sido de "+contadorIntentos+" puntos.");
                    comentario="Lo has 'clavao' papi";
                    Toast.makeText(getApplicationContext(),comentario, Toast.LENGTH_LONG).show();
                }
            }
        });
             rankingButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent (v.getContext(), RankingActivity.class);
                     startActivityForResult(intent, 0);

                 }
             });

        }


    }


