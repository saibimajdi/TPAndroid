package com.majdi.saibil.tpandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LastActivity extends AppCompatActivity {

    String nom, prenom, formation;
    TextView nom_tv, prenom_tv, formation_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        setTitle("Last Activity");

        Intent intent = getIntent();

        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        formation = intent.getStringExtra("formation");

        nom_tv = (TextView) findViewById(R.id.nom_final);
        prenom_tv = (TextView) findViewById(R.id.prenom_final);
        formation_tv = (TextView) findViewById(R.id.formation_final);

        nom_tv.setText("Nom : " + nom);
        prenom_tv.setText("Prenom : " + prenom);
        formation_tv.setText("Formation : " + formation);

    }
}
