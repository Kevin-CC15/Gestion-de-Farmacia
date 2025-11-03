package com.example.sistemafarmacia.Vista.categoria;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CategoriaCell extends ListCell<String> {
    private Button btnModificar = new Button("Modificar");
    private Button btnEliminar = new Button("Eliminar");
    private HBox botones = new HBox(btnModificar, btnEliminar);

    public CategoriaCell() {
        botones.setSpacing(5);
        btnModificar.setOnAction(e -> new EditarCategoriaDialogo(getItem()).showAndWait());
        btnEliminar.setOnAction(e -> {
            if (getListView() != null && getItem() != null)
                getListView().getItems().remove(getItem());
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty || item == null ? null : item);
        setGraphic(empty || item == null ? null : botones);
    }
}
