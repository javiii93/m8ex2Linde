package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   static ArrayList<Jugador>players=new ArrayList<>();
    Button checkButton;
    Button rankingButton;
    TextView textView1;
    EditText usernumber;
    int numberToSolve=new Random().nextInt(100);
    String comentario,nickname;
    int contadorIntentos,nombre;

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
                    textView1.setText("Congratulations, has adivinado el numero oculto, era el: "+numberToSolve+" Tu puntacion ha sido de "+contadorIntentos+" puntos, si quieres empezar de nuevo sigue escribiendo numeros");
                    comentario="Lo has 'clavao' papi";
                    Toast.makeText(getApplicationContext(),comentario, Toast.LENGTH_LONG).show();
                   dialogo();
                  }usernumber.setText("");
            }
        });
             rankingButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                   Intent intent = new Intent (v.getContext(), Ranking2Activity.class);
                     startActivity(intent);

                 }
             });

        }

    public void dialogo()
    {
final String menssage="Escriba su apodo para guardar su puntuacion, si no quiere pulse cancelar";

        final AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage(menssage);

        builder.setTitle("Hello makina, tu puntuacion es de "+contadorIntentos);

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
                                                int which)
                            {
                                nickname=input.getText().toString();


                               int numm=contadorIntentos;
                               Jugador j1=new Jugador(nickname,numm);
                               players.add(j1);
                                contadorIntentos=0;
                                numberToSolve=new Random().nextInt(100);
                               Intent i=new Intent(builder.getContext(),Ranking2Activity.class);
                                startActivity(i);

                  }
                        });


        builder
                .setNegativeButton(
                        "Cancelar",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                              finish();
                            }
                        });


        AlertDialog alertDialog = builder.create();

         alertDialog.show();
    }
}


