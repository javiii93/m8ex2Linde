package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
      Button checkButton;
    TextView usernumber;
    final int numberToSolve=new Random().nextInt(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkButton= findViewById(R.id.button);
        usernumber = findViewById(R.id.editText);
       // if(numberToSolve==usernumber.get)
     //   checkButton.setOnClickListener(new DialogInterface.OnClickListener())

        }


    }


