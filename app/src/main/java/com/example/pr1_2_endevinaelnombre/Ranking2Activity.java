package com.example.pr1_2_endevinaelnombre;
import android.os.Bundle;
import java.util.Collections;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;

public class Ranking2Activity extends AppCompatActivity {
String jug;
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
        Collections.sort(ma.players);
for(int i=0;i< ma.players.size();i++){
    jug=ma.players.get(i).nom+", "+ma.players.get(i).punts+" puntos";
    adaptador.add(jug);
}

}




}

