package ui;

import controlador.GestorDatos;
import vista.VentanaPrincipal;

import javax.swing.*;

/**
 * Clase principal para ejecutar el programa
 */

public class Main {

    public static void main(String[] args) {
        GestorDatos gestorDatos = new GestorDatos();

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(gestorDatos).setVisible(true);
        });


//





    }
}
