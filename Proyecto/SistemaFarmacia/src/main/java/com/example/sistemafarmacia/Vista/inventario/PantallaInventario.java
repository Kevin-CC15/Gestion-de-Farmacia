package com.example.sistemafarmacia.Vista.inventario;

import com.example.sistemafarmacia.Vista.navegacion.MenuPrincipal;
import com.example.sistemafarmacia.Vista.navegacion.PantallaBase;
import javafx.scene.control.Label;

public class PantallaInventario extends PantallaBase {
    public PantallaInventario(MenuPrincipal menuPrincipal) {
        super(menuPrincipal); // SIEMPRE pasar el menú
        this.getChildren().add(new Label("Pantalla de inventario funcionando."));
    }

    @Override
    public void mostrar() {}
}
