package com.example.sistema_farmacia.view.navegacion;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class PantallaBase extends BorderPane {
    protected MenuPrincipal menuPrincipal;
    protected Pane contenedorPrincipal;

    public PantallaBase() {
        menuPrincipal = new MenuPrincipal();
        contenedorPrincipal = new Pane();
        this.setTop(menuPrincipal);
        this.setCenter(contenedorPrincipal);
    }

    public void mostrar() {
        this.setVisible(true);
    }

    public void ocultar() {
        this.setVisible(false);
    }

    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
}
