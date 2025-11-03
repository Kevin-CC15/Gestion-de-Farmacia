package com.example.sistemafarmacia.Vista.navegacion;

import javafx.scene.layout.VBox;

/**
 * Clase abstracta que sirve como base para las pantallas principales.
 * Heredar de ella asegura que el menú principal esté SIEMPRE visible.
 */
public abstract class PantallaBase extends VBox {
    protected MenuPrincipal menuPrincipal;

    public PantallaBase(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.getChildren().add(menuPrincipal); // El menú SIEMPRE va arriba
    }

    /**
     * Método para actualizar la pantalla (opcional según tu diseño).
     */
    public abstract void mostrar();
}
