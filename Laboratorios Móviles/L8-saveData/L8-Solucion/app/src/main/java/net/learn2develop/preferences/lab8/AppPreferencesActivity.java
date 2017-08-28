package net.learn2develop.preferences.save;

        import android.os.Bundle;
        import  android.preference.PreferenceActivity;


public class AppPreferencesActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        addPreferencesFromResource(R.xml.myapppreferences);
    }
}

