package com.fsm.tp3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ArrayList<Vector<String>> arrayList;
    ListView listView;
    CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Gestionnaire des Etudiants");
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        listView = (ListView) findViewById(R.id.etudiants_lv);

        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this,arrayList,getLayoutInflater());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.putExtra("NOM", arrayList.get(i).get(0));
                intent.putExtra("PRENOM", arrayList.get(i).get(1));
                intent.putExtra("FORMATION", arrayList.get(i).get(2));
                intent.putExtra("INDEX", i);
                startActivityForResult(intent,1);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                buildShowDialog(i);
                return true;
            }
        });
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

    private void Quitter() {
        finish();
    }

    private void supprimerEtudiant() {
        arrayList.removeAll(arrayList);
        adapter.notifyDataSetChanged();
    }

    private void ajouterEtudiant() {
        Intent intent = new Intent(this,FormulaireActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "requestCode:" + requestCode, Toast.LENGTH_SHORT).show();
        if(requestCode == 0)
        {
            if(resultCode == MainActivity.RESULT_OK)
            {
                String nom = data.getStringExtra("NOM");
                String prenom = data.getStringExtra("PRENOM");
                String formation = data.getStringExtra("FORMATION");

                Vector<String> vector = new Vector<>();
                vector.add(nom);
                vector.add(prenom);
                vector.add(formation);

                arrayList.add(vector);
                adapter.notifyDataSetChanged();

                Toast.makeText(this, "Etudiant ajouté!", Toast.LENGTH_LONG).show();
            }

            if(resultCode == MainActivity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Annuler", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 1)
        {
            if(resultCode == MainActivity.RESULT_OK)
            {
                int index = data.getIntExtra("INDEX",-1);
                //Toast.makeText(this, "index:" + index, Toast.LENGTH_SHORT).show();
                if(index == -1)
                    Toast.makeText(this, "Erreur de suppression!", Toast.LENGTH_SHORT).show();
                arrayList.remove(index);
                adapter.notifyDataSetChanged();
            }
        }
    }

    void buildShowDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Vector<String> e = arrayList.get(position);
        builder.setTitle(e.get(0) + " " + e.get(1));
        String[] items = new String[]{"Supprimer","Details"};
        builder.setItems(items,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            arrayList.remove(position);
                            adapter.notifyDataSetChanged();
                        }else {
                            if(i == 1){
                                Vector<String> etudiant = arrayList.get(position);

                                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                                intent.putExtra("NOM", etudiant.get(0));
                                intent.putExtra("PRENOM", etudiant.get(1));
                                intent.putExtra("FORMATION", etudiant.get(2));
                                intent.putExtra("INDEX", position);
                                startActivityForResult(intent, 1);
                            }
                        }
                    }
                });
        builder.show();
    }

}
