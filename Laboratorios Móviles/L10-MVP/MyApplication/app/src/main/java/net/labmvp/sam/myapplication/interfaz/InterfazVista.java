package net.labmvp.sam.myapplication.interfaz;

import android.view.View;

/**
 * Created by Donny on 8/9/2016.
 */
public interface InterfazVista {
    void mostrarProgreso();
    void esconderProgreso();
    void mostrarErrorNombreUsuario();
    void mostrarErrorContrasenia();
    void cambiarActivity();

    void OnClick(View v);
}
