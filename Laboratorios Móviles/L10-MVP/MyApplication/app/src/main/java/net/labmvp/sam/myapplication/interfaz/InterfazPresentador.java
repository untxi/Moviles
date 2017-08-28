package net.labmvp.sam.myapplication.interfaz;

/**
 * Created by Donny on 8/9/2016.
 */
public interface InterfazPresentador {
    void validarCredenciales(String pUsuario,String pContrasenia);
    void destruir();

    void errorNombreUsuario();

    void errorContrasenia();

    void exitoAlIniciarSesion();
}
