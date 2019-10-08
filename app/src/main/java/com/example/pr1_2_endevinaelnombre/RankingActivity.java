package com.example.pr1_2_endevinaelnombre;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import  java.io.*;
public class RankingActivity extends AppCompatActivity {
TextView tx;
ArrayList<String>jugadores=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tx=findViewById(R.id.textView);
        try
        {OutputStream opsw=
                    new OutputStream(("prueba.txt", MODE_PRIVATE));
            opsw.close();
        }
        catch (Exception ex)
        {
        }

    }

}
