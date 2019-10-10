package com.example.pr1_2_endevinaelnombre;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;


import java.util.ArrayList;

public class Ranking2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListView lista;
        ArrayAdapter<String> adaptador;
        lista = findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);
        MainActivity ma=new MainActivity();
for(int i=0;i<MainActivity.players.size();i++){
    adaptador.add(MainActivity.players.get());

}
     /*   if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("player");
                adaptador.add(newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("player");
            }*/
}




}

