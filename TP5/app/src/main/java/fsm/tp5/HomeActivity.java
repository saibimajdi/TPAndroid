package fsm.tp5;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    EditText etNom, etPrenom, etLogin, etPassword;
    ListView lvEtudiants;
    ArrayList arrayList;
    ListAdapter adapter;
    Button btnAjouter, btnSupprimer, btnPreference, btnAuthOk;
    SharedPreferences preferences;

    View homeView, authView;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = openOrCreateDatabase("myDb.db",SQLiteDatabase.OPEN_READWRITE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS etudiant " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom VARCHAR," +
                "prenom VARCHAR);");


        LayoutInflater inflater = getLayoutInflater();
        homeView = inflater.inflate(R.layout.activity_home,null);
        authView = inflater.inflate(R.layout.activity_home_auth,null);

        // AUTH
        etLogin = (EditText) authView.findViewById(R.id.etLogin);
        etPassword = (EditText) authView.findViewById(R.id.etPassword);
        btnAuthOk = (Button) authView.findViewById(R.id.btnAuthOk);
        btnAuthOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            verifierLoginPassword();
            }
        });

        //HOME
        etNom = (EditText) homeView.findViewById(R.id.etNom);
        etPrenom = (EditText) homeView.findViewById(R.id.etPrenom);
        lvEtudiants = (ListView) homeView.findViewById(R.id.lvEtudiants);
        btnAjouter = (Button) homeView.findViewById(R.id.btnAjouter);
        btnSupprimer = (Button) homeView.findViewById(R.id.btnSupprimer);
        btnPreference = (Button) homeView.findViewById(R.id.btnPreference);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNom.getText().toString().equals("") || etPrenom.getText().toString().equals(""))
                    Toast.makeText(HomeActivity.this, "Champs obligatoires", Toast.LENGTH_SHORT).show();
                else
                    ajouterEtudiant(etNom.getText().toString(), etPrenom.getText().toString());
            }
        });

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimerEtudiants();
            }
        });

        btnPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPreferenceActivity();
            }
        });


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean useLogin = preferences.getBoolean("useLoginPassword", false);
        if(useLogin){
            setContentView(authView);
        }else{
            setContentView(homeView);
        }
        reRemplirListView();
        setTitle();

    }

    private void verifierLoginPassword(){
        String login = preferences.getString("login","");
        String password = preferences.getString("password","");

        if(etLogin.getText().toString().equals(login) && etPassword.getText().toString().equals(password)){
            setContentView(homeView);
        }else{
            Toast.makeText(HomeActivity.this, "Informations incorrects!", Toast.LENGTH_SHORT).show();
        }
    }

    private void ajouterEtudiant(String nom, String prenom){
        ContentValues values = new ContentValues();
        values.put("nom",nom);
        values.put("prenom",prenom);
        
        long id = db.insert("etudiant", null, values);
        if(id == -1){
            Toast.makeText(this, "Erreur d'insertion dans la base", Toast.LENGTH_SHORT).show();
            return;
        }
        etNom.setText("");
        etPrenom.setText("");
        reRemplirListView();
    }

    private void supprimerEtudiants(){
        db.execSQL("DELETE FROM etudiant;");
        reRemplirListView();
    }

    private void reRemplirListView(){
        ListView lsEtudiants = (ListView) homeView.findViewById(R.id.lvEtudiants);
        ArrayList listEtudiants = new ArrayList();

        Cursor resultSet = db.rawQuery("SELECT * FROM etudiant", null);
        if(resultSet.moveToFirst()){
            do{
                String id = resultSet.getString(0);
                String nom = resultSet.getString(1);
                String prenom = resultSet.getString(2);
                String text = "[" + id + "]: " + prenom + " " + nom;

                listEtudiants.add(text);
            }while(resultSet.moveToNext());
        }

        resultSet.close();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listEtudiants);
        lvEtudiants.setAdapter(adapter);
    }

    private void startPreferenceActivity(){
        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setTitle();
    }

    private void setTitle(){
        String title = preferences.getString("title","Mon application");
        setTitle(title);
    }
}
