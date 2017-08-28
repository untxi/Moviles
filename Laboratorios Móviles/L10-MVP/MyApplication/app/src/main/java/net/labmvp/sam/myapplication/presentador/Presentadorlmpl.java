package net.labmvp.sam.myapplication.presentador;

import android.util.Log;

import net.labmvp.sam.myapplication.interfaz.InterfazLoginListener;
import net.labmvp.sam.myapplication.interfaz.InterfazModelo;
import net.labmvp.sam.myapplication.interfaz.InterfazPresentador;
import net.labmvp.sam.myapplication.interfaz.InterfazVista;
import net.labmvp.sam.myapplication.modelo.Modelolmpl;

public class Presentadorlmpl implements InterfazPresentador {
     private InterfazVista login;
    private InterfazModelo modelo;

    public Presentadorlmpl(InterfazVista login){
        this.login=login;
        this.modelo = new Modelolmpl();
    }
    @Override
    public void validarCredenciales(String pUsuario, String pContrasenia) {
        if(login!=null){
            login.mostrarProgreso();
        }
        modelo.login(pUsuario, pContrasenia, (InterfazLoginListener) this);
    }

    @Override
    public void destruir() { login=null; }

    @Override
    public void errorNombreUsuario(){
        if(login!=null){
            login.mostrarErrorNombreUsuario();
            login.esconderProgreso();
        }
    }

    @Override
    public void errorContrasenia(){
        if(login!=null){
            login.mostrarErrorContrasenia();
            login.esconderProgreso();
        }
    }

    @Override
    public void exitoAlIniciarSesion(){
        if(login!=null){
            login.cambiarActivity();
        }
    }
}
