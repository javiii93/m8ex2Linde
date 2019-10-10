package com.example.pr1_2_endevinaelnombre;

public class Jugador implements Comparable<Jugador>{
    public String nom;
    public int punts;

    public Jugador(String nom, int punts) {
        this.nom = nom;
        this.punts=punts;
    }

    @Override
    public int compareTo(Jugador o) {


        if (this.punts<o.punts) {  return -1;     }

        else if (this.punts>o.punts) {   return 1;     }


        return 0;


    }
}
