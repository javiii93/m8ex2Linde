package com.example.pr1_2_endevinaelnombre;

import android.graphics.Bitmap;

public class Jugador implements Comparable<Jugador>{
    public String nom;
    public int punts;
    public String currentPhotoPath;
    public Bitmap bitmap;
    public Jugador(String nom, int punts) {
        this.nom = nom;
        this.punts=punts;
    }

    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Jugador(){};
    @Override
    public int compareTo(Jugador o) {


        if (this.punts<o.punts) {  return -1;     }

        else if (this.punts>o.punts) {   return 1;     }


        return 0;


    }

    @Override
    public String toString() {
        return nom +", score: " + punts;
    }
}
