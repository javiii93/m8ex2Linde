package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {
    ArrayList<Integer>jugadores=new ArrayList<>();


    Button volver;
    TextView nombresDeRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        volver= findViewById(R.id.button5);
        nombresDeRanking= findViewById(R.id.textView3);
        for(int i=0;i<jugadores.size();i++){
            nombresDeRanking.setText(nombresDeRanking.getText()+"\n"+jugadores.get(i).toString());
        }
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            MainActivity mn=new MainActivity();
            setContentView(R.layout.activity_main);
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
