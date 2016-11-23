package com.fsm.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView tvNom, tvPrenom, tvFormation;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        initComponents();
        Intent intent = getIntent();
        tvNom.setText(intent.getStringExtra("NOM"));
        tvPrenom.setText(intent.getStringExtra("PRENOM"));
        tvFormation.setText(intent.getStringExtra("FORMATION"));
        index = intent.getIntExtra("INDEX", -1);

        setTitle(tvNom.getText().toString() + " " + tvPrenom.getText().toString());
    }

    private void initComponents(){
        tvNom = (TextView) findViewById(R.id.info_nom);
        tvPrenom = (TextView) findViewById(R.id.info_prenom);
        tvFormation = (TextView) findViewById(R.id.info_formation);
    }

    public void onClick_RetourButton(View view) {
        setResult(InfoActivity.RESULT_CANCELED);
        finish();
    }

    public void onClick_SupprimerButton(View view) {
        Intent intent = new Intent();
        intent.putExtra("INDEX", index);
        setResult(InfoActivity.RESULT_OK, intent);
        finish();
    }
}
