package com.fsm.tp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LastActivity extends AppCompatActivity {
    String nom, prenom, formation;
    TextView tvNom, tvPrenom, tvFormation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        setTitle("Etape 3/3");

        tvNom = (TextView) findViewById(R.id.tv_nom_3);
        tvPrenom = (TextView) findViewById(R.id.tv_prenom_3);
        tvFormation = (TextView) findViewById(R.id.tv_formation_3);

        Intent intent = getIntent();
        nom = intent.getStringExtra("NOM");
        prenom = intent.getStringExtra("PRENOM");
        formation = intent.getStringExtra("FORMATION");

        tvNom.setText(nom);
        tvPrenom.setText(prenom);
        tvFormation.setText(formation);


    }

    public void confirmer(View view) {

        Intent intent = new Intent();
        intent.putExtra("NOM", nom);
        intent.putExtra("PRENOM", prenom);
        intent.putExtra("FORMATION", formation);

        setResult(Activity.RESULT_OK, intent);
        Toast.makeText(this, "pass!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void annuler(View view) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
