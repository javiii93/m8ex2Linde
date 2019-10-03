package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {
    ArrayList<Integer>jugadores=new ArrayList<>();


    Toolbar barra;
    TextView nombresDeRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        barra= findViewById(R.id.toolbar);
        nombresDeRanking= findViewById(R.id.textView3);
        for(int i=0;i<jugadores.size();i++){
            nombresDeRanking.setText(nombresDeRanking.getText()+"\n"+jugadores.get(i).toString());
        }
         barra = (Toolbar) findViewById(R.id.toolbar);
     barra.setNavigationIcon(R.drawable.ic_menu_revert);
        barra.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

    }
    public ArrayList<Integer> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Integer> jugadores) {
        this.jugadores = jugadores;
    }

}
