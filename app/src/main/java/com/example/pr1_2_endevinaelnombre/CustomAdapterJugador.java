package com.example.pr1_2_endevinaelnombre;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CustomAdapterJugador extends BaseAdapter {
   private ArrayList<Jugador>arrJugadors=new ArrayList<>();
    private Context context;

    public CustomAdapterJugador(ArrayList<Jugador> arrJugadors, Context context) {
        this.arrJugadors = arrJugadors;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrJugadors.size();
    }

    @Override
    public Jugador getItem(int position) {
        return arrJugadors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }
}
