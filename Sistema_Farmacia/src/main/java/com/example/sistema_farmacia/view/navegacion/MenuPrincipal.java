package com.example.sistema_farmacia.view.navegacion;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuPrincipal extends HBox {
    public Button btnVentas;
    public Button btnCategorias;
    public Button btnClientes;
    public Button btnProductos;
    public Button btnRecibos;
    public Button btnReportes;
    public Button btnUsuariosSesion;

    public MenuPrincipal() {
        btnVentas = new Button("Ventas");
        btnCategorias = new Button("Categorías");
        btnClientes = new Button("Clientes");
        btnProductos = new Button("Productos");
        btnRecibos = new Button("Recibos");
        btnReportes = new Button("Reportes");
        btnUsuariosSesion = new Button("Usuarios/Sesión");

        this.setSpacing(12);
        this.setPadding(new Insets(12));
        this.getChildren().addAll(btnVentas, btnCategorias, btnClientes, btnProductos, btnRecibos, btnReportes, btnUsuariosSesion);
    }
}
