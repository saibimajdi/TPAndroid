package com.fsm.tp3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormulaireActivity extends AppCompatActivity {

    EditText nomEt, prenomEt, formationEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        setTitle("Ajouter Etudiant");

        initComponents();

    }

    private void initComponents(){
        nomEt = (EditText) findViewById(R.id.nom_formAct);
        prenomEt = (EditText) findViewById(R.id.prenom_formAct);
        formationEt = (EditText) findViewById(R.id.formation_formAct);
    }

    public void onClick_OKButton(View view) {

        Intent intent = new Intent();
        intent.putExtra("NOM", nomEt.getText().toString());
        intent.putExtra("PRENOM", prenomEt.getText().toString());
        intent.putExtra("FORMATION", formationEt.getText().toString());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onClick_AnnulerButton(View view) {

        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();

    }
}
