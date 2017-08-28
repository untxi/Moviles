package net.labmvp.sam.myapplication.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.labmvp.sam.myapplication.R;
import net.labmvp.sam.myapplication.interfaz.InterfazPresentador;
import net.labmvp.sam.myapplication.interfaz.InterfazVista;
import net.labmvp.sam.myapplication.presentador.Presentadorlmpl;

public class Login extends AppCompatActivity implements InterfazVista{


    private Button boton;
    private EditText usuario, contrasenia;
    private ProgressBar barraProgreso;
    private InterfazPresentador presentador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraProgreso = (ProgressBar) findViewById(R.id.progress);
        usuario = (EditText) findViewById(R.id.username);
        contrasenia = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener((View.OnClickListener)this);

        presentador = new Presentadorlmpl(this);
    }

    @Override
    public void mostrarProgreso() {
        barraProgreso.setVisibility(View.VISIBLE);
    }

    @Override
    public void esconderProgreso() {
        barraProgreso.setVisibility(View.GONE);
    }

    @Override
    public void mostrarErrorNombreUsuario() {
        usuario.setError("Campo Obligatorio");

    }

    @Override
    public void mostrarErrorContrasenia() {
        contrasenia.setError("Campo Obligatorio");
    }

    @Override
    public void cambiarActivity() {
        Toast.makeText(Login.this, "Inicio sesión con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnClick(View v){
        presentador.validarCredenciales(usuario.getText().toString(), contrasenia.getText().toString());
    }
}
