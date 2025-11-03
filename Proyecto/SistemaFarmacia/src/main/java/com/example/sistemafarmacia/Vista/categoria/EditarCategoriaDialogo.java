package com.example.sistemafarmacia.Vista.categoria;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class EditarCategoriaDialogo extends Dialog<Void> {
    private TextField tfNombre = new TextField();
    private TextField tfDescripcion = new TextField();

    public EditarCategoriaDialogo(String nombreCategoria) {
        setTitle("Editar Categoría");
        tfNombre.setText(nombreCategoria);
        GridPane grid = new GridPane();
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(tfNombre, 1, 0);
        grid.add(new Label("Descripción:"), 0, 1);
        grid.add(tfDescripcion, 1, 1);
        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(bt -> null);
    }
}
