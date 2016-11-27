package com.fsm.tp4;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class InfoActivity extends AppCompatActivity {
    ArrayList<Vector<String>> listEtudiants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(getIntent().getExtras());

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.llInfoContainer, fragment).commit();

        listEtudiants = new ArrayList<Vector<String>>();
        Vector<String> et1 = new Vector<String>();
        et1.add("Majdi");
        et1.add("Saibi");
        et1.add("MP ISI 1");

        Vector<String> et2 = new Vector<String>();
        et1.add("Majdi");
        et1.add("Saibi");
        et1.add("MP ISI 1");

        listEtudiants.add(et1);
        listEtudiants.add(et2);



    }
}
