package net.learn2develop.preferences.lab8;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write("Escribe");
            osw.close();

            Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }


        try{
            FileInputStream fIn = openFileInput("textfile.txt");

            InputStreamReader isr = new InputStreamReader(fIn);

            Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }


    }// on create
}


/*package net.learn2develop.preferences.save;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    String prefName = "MyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //PreferenceManager prefMgr = getPreferenceManager();
        //prefMgr.setSharedPreferencesName("appPreferences");

        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(prefName, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("temperature", 85);
        editor.putBoolean("authenticated", true);
        editor.putString("username", "Sammy");

        editor.commit();

        readPrefValues();

        Intent i = new Intent("net.learn2develop.AppPreferenceActivity");
        startActivity(i);

        SharedPreferences appPrefs = getSharedPreferences(
                "net.learn2develop.PreferenceScreen_preferences",
                MODE_PRIVATE);


        SharedPreferences appPrefs = getSharedPreferences("appPreferences", MODE_PRIVATE);

        Toast.makeText(getBaseContext(),
                appPrefs.getString("editTextPref", ""),
                Toast.LENGTH_LONG).show();
    }

    public void readPrefValues(){
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        float tempertature = prefs.getFloat("temperature", 50);
        boolean authenticated = prefs.getBoolean("authenticated", false);
        String username = prefs.getString("username", "");
    }


}
*/