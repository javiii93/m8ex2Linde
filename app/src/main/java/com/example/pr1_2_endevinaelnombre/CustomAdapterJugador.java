package com.example.pr1_2_endevinaelnombre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class CustomAdapterJugador extends BaseAdapter implements ListAdapter {
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
        View view=convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView) ;
        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string1);
       imageView.setImageBitmap(arrJugadors.get(position).getBitmap());
       /* ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrJugadors.get(position).getArray());
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        imageView.setImageBitmap(bitmap);*/
       listItemText.setText(arrJugadors.get(position).toString());
        return view;
    }
}
