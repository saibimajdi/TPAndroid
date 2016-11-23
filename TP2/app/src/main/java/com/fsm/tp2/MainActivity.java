package com.fsm.tp2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Etudiant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gestionnaire des Etudiants");

        listView = (ListView) findViewById(R.id.etudiants_lv);
        arrayList = new ArrayList<Etudiant>();
        arrayList.add(new Etudiant("Majdi", "SAIBI", "Master Pro ISI 2"));
        arrayList.add(new Etudiant("Oussama", "SAIBI", "LFI 2"));
        arrayList.add(new Etudiant("Nizar", "AWLED OMARA", "Master Pro ISI 2"));
        configListView();

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.miNouveau :
                ajouterEtudiant();
                break;
            case R.id.miSupprimer:
                supprimerEtudiant();
                break;
            case R.id.miQuitter :
                Quitter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ajouterEtudiant(){
        Intent intent = new Intent(this, FirstActivity.class);
        startActivityForResult(intent, 0);
    }

    private void supprimerEtudiant(){
        arrayList = new ArrayList<>();
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
    }

    private void Quitter(){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Intent intent = data;

            String nom, prenom, formation;
            nom = intent.getStringExtra("NOM");
            prenom = intent.getStringExtra("PRENOM");
            formation = intent.getStringExtra("FORMATION");

            //Toast.makeText(this, nom + " " + prenom + " => " + formation, Toast.LENGTH_SHORT).show();

            // listView config
            arrayList.add(
                    new Etudiant(nom, prenom, formation));
            CustomArrayAdapter adapter = new CustomArrayAdapter(this, arrayList, getLayoutInflater());
            listView.setAdapter(adapter);

        }else{
            if(resultCode == RESULT_CANCELED)
                Toast.makeText(this, "cancled", Toast.LENGTH_SHORT).show();
        }
    }

    public void configListView()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Etudiant etudiant = arrayList.get(i);
                buildShowDialog(i);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Etudiant etudiant = arrayList.get(i);
                buildShowDialog(i);
                return true;
            }
        });
    }

    void buildShowDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Etudiant e = arrayList.get(position);
        builder.setTitle(e.Nom + " " + e.Prenom);
        String[] items = new String[]{"Supprimer","Details"};
        builder.setItems(items,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            arrayList.remove(position);
                            CustomArrayAdapter adapter = new CustomArrayAdapter(getApplicationContext(),
                                    arrayList,getLayoutInflater());
                            listView.setAdapter(adapter);
                        }else {
                            if(i == 1){
                                Etudiant etudiant = arrayList.get(position);
                                Toast.makeText(MainActivity.this, etudiant.Nom + " " + etudiant.Prenom, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        builder.show();
    }
}
