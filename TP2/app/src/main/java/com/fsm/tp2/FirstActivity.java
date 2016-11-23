package com.fsm.tp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    EditText etNom, etPrenom;
    String nom, prenom, formation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setTitle("Etape 1/3");

        etNom = (EditText) findViewById(R.id.et_nom_1);
        etPrenom = (EditText) findViewById(R.id.et_prenom_1);
    }

    public void suivant1(View view) {

        if(etNom.getText().toString().equals("") || etPrenom.getText().toString().equals("")){
            Toast.makeText(this, "Champs obligatoires!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("NOM", etNom.getText().toString());
            intent.putExtra("PRENOM", etPrenom.getText().toString());
            startActivityForResult(intent, 0);
        }
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //super.onActivityResult(requestCode, resultCode, data);
            Intent intent = data;
            if(resultCode == RESULT_OK){

                nom = intent.getStringExtra("NOM");
                prenom = intent.getStringExtra("PRENOM");
                formation = intent.getStringExtra("FORMATION");

                setResult(Activity.RESULT_OK, intent);
            }
            else{
                setResult(Activity.RESULT_CANCELED, intent);
            }
            finish();
    }
}
