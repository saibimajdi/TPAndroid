package com.fsm.tp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    EditText etFormation;
    TextView tvNom, tvPrenom;
    String nom, prenom, formation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Etape 2/3");

        tvNom = (TextView) findViewById(R.id.tv_nom_2);
        tvPrenom = (TextView) findViewById(R.id.tv_prenom_2);
        etFormation = (EditText) findViewById(R.id.et_formation_2);

        Intent intent = getIntent();
        nom = intent.getStringExtra("NOM");
        prenom = intent.getStringExtra("PRENOM");

        tvNom.setText(nom);
        tvPrenom.setText(prenom);

    }

    public void suivant2(View view) {
        if(etFormation.getText().toString().equals("")){
            Toast.makeText(this, "Champs obligatoires", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, LastActivity.class);
            intent.putExtra("FORMATION", etFormation.getText().toString());
            intent.putExtra("NOM", nom);
            intent.putExtra("PRENOM", prenom);
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
