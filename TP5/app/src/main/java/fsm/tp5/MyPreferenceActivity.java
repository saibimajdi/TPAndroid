package fsm.tp5;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyPreferenceActivity extends AppCompatActivity {
    MyPreferenceFragment myPreferenceFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_preference);
        setTitle("Preferences utilisateur");
        myPreferenceFragment = new MyPreferenceFragment();

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.llMyPreferenceContainer, myPreferenceFragment).commit();
    }


}
