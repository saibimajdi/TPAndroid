package fsm.tp5;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by saibi on 12/8/2016.
 */

public class MyPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
