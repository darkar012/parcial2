package com.example.parcial2android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class baseAdapter extends BaseAdapter {

    private ArrayList<Pelicula> peliculas;

    public baseAdapter() {
        peliculas = new ArrayList<>();
    }

    public void clear() {
        peliculas.clear();
        notifyDataSetChanged();
    }

    public void addTareas(Pelicula tarea) {
        peliculas.add(tarea);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View renglon, ViewGroup lista) {
        LayoutInflater inflater = LayoutInflater.from(lista.getContext());
        View renglonView = inflater.inflate(R.layout.row, null);
        Pelicula peli = peliculas.get(pos);

        TextView titulo = renglonView.findViewById(R.id.tituloPelicula);
        TextView prom = renglonView.findViewById(R.id.prom);

        titulo.setText(peli.getId());
        DecimalFormat format = new DecimalFormat("#.0");

        String pr = String.valueOf(format.format(peli.getVotos()));
        prom.setText(pr);

        return renglonView;
    }
}
