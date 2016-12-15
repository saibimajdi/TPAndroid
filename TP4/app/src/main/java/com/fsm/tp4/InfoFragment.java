package com.fsm.tp4;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class InfoFragment extends Fragment {
    TextView nomtv, prenomtv, formationtv;
    String nom, prenom, formation;
    OnStudentEventListener listener;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listener = (OnStudentEventListener) getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        nomtv = (TextView) view.findViewById(R.id.nom_tv);
        prenomtv = (TextView) view.findViewById(R.id.prenom_tv);
        formationtv = (TextView) view.findViewById(R.id.formation_tv);

        Button bSupprimer = (Button) view.findViewById(R.id.buttonSupprimer);
        Button bSuivant = (Button) view.findViewById(R.id.buttonSuivant);

        bSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSuivantClicked();
            }
        });

        bSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSupprimerClicked();
            }
        });

        listener.onSuivantClicked();
        return view;
    }

    public void afficherEtudiant(String nom, String prenom, String formation){
        Toast.makeText(getActivity(), "nom: " + nom + " prenom:" + prenom + " formation:" + formation, Toast.LENGTH_SHORT).show();
        if(nomtv == null)
            return;

        nomtv.setText(nom);
        prenomtv.setText(prenom);
        formationtv.setText(formation);
    }




}
