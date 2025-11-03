package com.example.sistemafarmacia.Vista.categoria;

import com.example.sistemafarmacia.Vista.navegacion.MenuPrincipal;
import com.example.sistemafarmacia.Vista.navegacion.PantallaBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PantallaCategorias extends PantallaBase {
    @FXML
    private Button btnSubAgregar = new Button("Agregar");
    @FXML
    private Button btnSubListar = new Button("Listar");
    private SubPaginaAgregarCategoria subAgregar = new SubPaginaAgregarCategoria();
    private SubPaginaListarCategoria subListar = new SubPaginaListarCategoria();
    private SubPaginaBaseCategoria subActual;

    public PantallaCategorias(MenuPrincipal menuPrincipal) {
        super(menuPrincipal);
        btnSubAgregar.setOnAction(e -> mostrarSubPagina(subAgregar));
        btnSubListar.setOnAction(e -> mostrarSubPagina(subListar));
        HBox barraBotones = new HBox(btnSubAgregar, btnSubListar);
        barraBotones.setSpacing(12);
        this.getChildren().add(barraBotones);
        mostrarSubPagina(subListar); // Por defecto la lista
    }

    public void mostrarSubPagina(SubPaginaBaseCategoria sub) {
        if (subActual != null && this.getChildren().contains((VBox) subActual))
            this.getChildren().remove((VBox) subActual);
        subActual = sub;
        subActual.mostrar();
        this.getChildren().add((VBox) subActual);
    }

    @Override
    public void mostrar() {}
}
