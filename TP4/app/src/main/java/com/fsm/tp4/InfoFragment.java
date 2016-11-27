package com.fsm.tp4;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoFragment extends Fragment implements OnStudentEventListener {
    TextView nomtv, prenomtv, formationtv;
    String nom, prenom, formation;
    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Bundle args = getArguments();

        if(args != null){
            nom = args.getString("NOM");
            prenom = args.getString("PRENOM");
            formation = args.getString("FORMATION");
        }

        nomtv = (TextView) view.findViewById(R.id.nom_tv);
        prenomtv = (TextView) view.findViewById(R.id.prenom_tv);
        formationtv = (TextView) view.findViewById(R.id.formation_tv);

        return view;
    }

    public void afficherEtudiant(String nom, String prenom, String formation){
        // TODO
    }

    @Override
    public void onSupprimerClicked() {

    }

    @Override
    public void onSuivantClicked() {

    }



}
