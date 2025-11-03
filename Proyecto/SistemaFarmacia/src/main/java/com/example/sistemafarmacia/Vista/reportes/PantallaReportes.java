package com.example.sistemafarmacia.Vista.reportes;

import com.example.sistemafarmacia.Vista.navegacion.MenuPrincipal;
import com.example.sistemafarmacia.Vista.navegacion.PantallaBase;
import javafx.scene.control.Label;

public class PantallaReportes extends PantallaBase {
    public PantallaReportes(MenuPrincipal menuPrincipal) {
        super(menuPrincipal);
        this.getChildren().add(new Label("Pantalla de reportes funcionando."));
    }

    @Override
    public void mostrar() {}
}
