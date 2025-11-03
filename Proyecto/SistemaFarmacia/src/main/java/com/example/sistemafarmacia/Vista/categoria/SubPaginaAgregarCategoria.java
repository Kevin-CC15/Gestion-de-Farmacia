package com.example.sistemafarmacia.Vista.categoria;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SubPaginaAgregarCategoria extends VBox implements SubPaginaBaseCategoria {
    private TextField tfNombreAgrCat = new TextField();
    private TextField tfDescripcionAgrCat = new TextField();
    private Button btnAceptarAgrCat = new Button("Aceptar");

    public SubPaginaAgregarCategoria() {
        GridPane grid = new GridPane();
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(tfNombreAgrCat, 1, 0);
        grid.add(new Label("Descripción:"), 0, 1);
        grid.add(tfDescripcionAgrCat, 1, 1);
        grid.add(btnAceptarAgrCat, 1, 2);
        this.getChildren().add(grid);
        btnAceptarAgrCat.setOnAction(e -> limpiarCampos());
    }

    public void mostrar() { limpiarCampos(); }
    public void limpiarCampos() {
        tfNombreAgrCat.clear();
        tfDescripcionAgrCat.clear();
    }
}
