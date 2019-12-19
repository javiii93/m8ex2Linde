package com.example.pr1_2_endevinaelnombre;

import android.os.Bundle;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.*;


import static com.example.pr1_2_endevinaelnombre.MainActivity.players;

public class Ranking2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListView lista;
        Collections.sort(players);
        lista = findViewById(R.id.listView);
        CustomAdapterJugador adaptador = new CustomAdapterJugador(players, this);
        lista.setAdapter(adaptador);
    }

}

