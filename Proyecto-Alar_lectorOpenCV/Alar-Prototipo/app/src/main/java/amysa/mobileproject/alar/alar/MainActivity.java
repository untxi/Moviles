package amysa.mobileproject.alar.alar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static final String EXTRA_MESSAGE = "@string/extra";
    private ArrayList<data> students;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.helpMenu:
                goHelp();
                break;
            case R.id.aboutMenu:
                goAbout();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);

    }


    public void goHelp(){
        Intent intent = new Intent(MainActivity.this, help.class);
        startActivity(intent);
    }

    public void goAbout(){
        Intent intent = new Intent(MainActivity.this, about.class);
        startActivity(intent);
    }

    public void goCapture(View view) {
        Intent intent = new Intent(MainActivity.this, Capture.class);
        startActivity(intent);
    }

    public void seeStudentList(View view){
        Intent intent = new Intent(MainActivity.this, seeStudentList.class);
        startActivity(intent);
    }

    public void sendResult(View view){

        students = ((MyApplication) this.getApplication()).getStudents();

        Toast.makeText(getApplicationContext(),students.get(0).getStudentName(),Toast.LENGTH_LONG);

        String filename = "myfile.txt";
        String nombre = "ESTUDIANTE";
        FileOutputStream outputStream;
        String[] letras =  {"A","B","C","D"};

        try{
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            Random r = new Random();
            for (int i=0;i<10;i++){
                int i1 = r.nextInt(3 - 0 + 1) + 0;
                String letra = letras[i1];
                osw.write(letra);
            }

            osw.close();

            Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }


        Log.d("Path",MainActivity.this.getFilesDir().getAbsolutePath());
        File file = new File(this.getFilesDir(), filename);
        String data = file.getAbsolutePath();
        Log.d("PATH2",data);
        /*
        Intent intent = new Intent(MainActivity.this, sendResult.class);
        startActivity(intent);
        */
    }



}