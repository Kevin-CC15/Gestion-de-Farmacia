package com.example.sistemafarmacia.Vista.principal;

import com.example.sistemafarmacia.Vista.navegacion.MenuPrincipal;
import com.example.sistemafarmacia.Vista.navegacion.PantallaBase;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PantallaPrincipal extends PantallaBase {
    public PantallaPrincipal(MenuPrincipal menuPrincipal) {
        super(menuPrincipal); // El menú siempre va arriba
        this.getChildren().add(new Label("Pantalla principal funcionando."));
        // AGREGAR TEST:
        System.out.println("Hijos de PantallaPrincipal: " + this.getChildren());
    }
    @Override
    public void mostrar() {}
}


