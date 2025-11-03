package com.example.sistemafarmacia.Vista.categoria;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class SubPaginaListarCategoria extends VBox implements SubPaginaBaseCategoria {
    private TextField tfBuscarCategoria = new TextField();
    private ListView<String> listVListaCategoria = new ListView<>();

    public SubPaginaListarCategoria() {
        tfBuscarCategoria.setPromptText("Buscar...");
        this.getChildren().add(new HBox(new Label("Buscar:"), tfBuscarCategoria));
        this.getChildren().add(listVListaCategoria);
        filtrarCategorias("");
        tfBuscarCategoria.setOnKeyReleased(e -> filtrarCategorias(tfBuscarCategoria.getText()));
    }

    public void mostrar() { filtrarCategorias(""); }
    public void filtrarCategorias(String txt) {
        List<String> datos = txt.isEmpty() ?
                List.of("Medicamentos", "Vitaminas", "Botiquines") :
                List.of("Categorias filtradas: " + txt);
        listVListaCategoria.setItems(FXCollections.observableArrayList(datos));
        listVListaCategoria.setCellFactory(lv -> new CategoriaCell());
    }
}

