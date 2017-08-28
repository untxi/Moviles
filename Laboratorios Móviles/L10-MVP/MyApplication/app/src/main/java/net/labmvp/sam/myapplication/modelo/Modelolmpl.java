package net.labmvp.sam.myapplication.modelo;


import android.os.Handler;
import android.text.TextUtils;

import net.labmvp.sam.myapplication.interfaz.InterfazLoginListener;
import net.labmvp.sam.myapplication.interfaz.InterfazModelo;


public class Modelolmpl implements InterfazModelo {
    @Override
    public void login(final String pNombre, final String pContrasenia, final InterfazLoginListener listener){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(pNombre)) {
                    listener.errorNombreUsuario();
                    error = true;
                }
                if (TextUtils.isEmpty(pContrasenia)) {
                    listener.errorContrasenia();
                    error = true;
                }
                if (!error) {
                    listener.exitoAlIniciarSesion();
                }
            }
        },2000);
    }
}
