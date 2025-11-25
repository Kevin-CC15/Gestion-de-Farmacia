package com.example.sistema_farmacia.view.navegacion;

import javafx.scene.Parent;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador encargado de gestionar la navegación entre pantallas principales (FXML).
 * Almacena los nodos visuales cargados desde FXML bajo un nombre clave.
 */
public class ControladorNavegacion {
    private Parent pantallaActual;
    private final Map<String, Parent> pantallas;

    public ControladorNavegacion() {
        pantallas = new HashMap<>();
    }

    /**
     * Registra una nueva pantalla visual (nodo Parent) bajo el nombre indicado.
     * @param nombre clave para identificar la pantalla.
     * @param pantalla nodo JavaFX cargado por FXMLLoader.
     */
    public void agregarPantalla(String nombre, Parent pantalla) {
        pantallas.put(nombre, pantalla);
    }

    /**
     * Cambia la pantalla activa por la indicada en el mapa.
     * @param nombre clave de la pantalla a mostrar.
     * @return el nodo Parent a usar en la escena principal.
     */
    public Parent cambiarPantalla(String nombre) {
        pantallaActual = pantallas.get(nombre);
        return pantallaActual;
    }

    /**
     * Método para cerrar la aplicación desde navegación.
     */
    public void salirAplicacion() {
        System.exit(0);
    }
}
