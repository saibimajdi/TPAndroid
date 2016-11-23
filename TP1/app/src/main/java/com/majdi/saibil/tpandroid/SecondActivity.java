package com.majdi.saibil.tpandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView nom_tv, prenom_tv;
    EditText formation;
    String nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("Second Activity");

        nom_tv = (TextView) findViewById(R.id.nom_et_second);
        prenom_tv = (TextView) findViewById(R.id.prenom_et_second);
        formation = (EditText) findViewById(R.id.formation_et);

        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");

        if(nom != null && prenom != null){
            nom_tv.setText(nom);
            prenom_tv.setText(prenom);
        }


    }

    public void onClickSuivantSecond(View view) {
        Intent intent = new Intent(this, LastActivity.class);

        intent.putExtra("nom",nom);
        intent.putExtra("prenom",prenom);
        intent.putExtra("formation",formation.getText().toString());

        startActivity(intent);
    }
}
