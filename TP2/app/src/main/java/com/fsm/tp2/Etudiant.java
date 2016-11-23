package com.fsm.tp2;

/**
 * Created by saibi on 11/03/2016.
 */

public class Etudiant {

    public String Nom;
    public String Prenom;
    public String Formation;

    public Etudiant(){}
    public Etudiant(String nom, String prenom, String formation){

        this.Nom = nom;
        this.Prenom = prenom;
        this.Formation = formation;
    }

    @Override
    public String toString() {

        return Nom + " " + Prenom + " :" + Formation;
    }
}
