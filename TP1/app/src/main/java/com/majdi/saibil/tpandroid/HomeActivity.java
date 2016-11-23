package com.majdi.saibil.tpandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    EditText nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home Activity");

        nom = (EditText) findViewById(R.id.nom_et);
        prenom = (EditText) findViewById(R.id.prenom_et);
    }

    public void buttonNextOnClick(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("nom",nom.getText().toString());
        intent.putExtra("prenom", prenom.getText().toString());

        startActivity(intent);
    }
}
