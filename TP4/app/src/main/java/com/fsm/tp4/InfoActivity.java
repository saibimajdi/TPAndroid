package com.fsm.tp4;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class InfoActivity extends AppCompatActivity implements OnStudentEventListener {

    ArrayList listEtudiants;
    InfoFragment fragment;
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle("Infor étudiant");

        fragment= new InfoFragment();
        fragment.setArguments(getIntent().getExtras());

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.llInfoContainer, fragment).commit();

        listEtudiants = new ArrayList();

        Vector<String> et = new Vector();
        et.add("Majdi1");
        et.add("Saibi1");
        et.add("MP ISI 1");
        listEtudiants.add(et);

        et = new Vector();
        et.add("Majdi2");
        et.add("Saibi2");
        et.add("MP ISI 2");
        listEtudiants.add(et);

        et = new Vector();
        et.add("Majdi3");
        et.add("Saibi3");
        et.add("MP ISI 3");
        listEtudiants.add(et);

        selectedItem = listEtudiants.size()-1;


    }

    public void afficherEtudiant(){
        if(selectedItem < 0){
            fragment.afficherEtudiant("","","");
            return;
        }

        Vector<String> v = (Vector) listEtudiants.get(selectedItem);
        fragment.afficherEtudiant((String)v.get(0), (String)v.get(1), (String)v.get(2));
    }

    public void onSupprimerClicked(){
        if(selectedItem < 0)
            return;

        listEtudiants.remove(selectedItem);

        if(listEtudiants.size() > selectedItem){
            afficherEtudiant();
        }
        else{
            if(listEtudiants.size() > 0){
                selectedItem = 0;
                afficherEtudiant();
            }
            else{
                selectedItem = -1;
                afficherEtudiant();
            }
        }
    }

    public void onSuivantClicked(){
        if(selectedItem < 0)
            return;

        selectedItem++;

        if(selectedItem >= listEtudiants.size()){
            selectedItem = 0;
        }

        afficherEtudiant();
    }
}
