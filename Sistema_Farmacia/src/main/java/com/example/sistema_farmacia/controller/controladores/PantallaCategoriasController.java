package com.example.sistema_farmacia.controller.controladores;

import com.example.sistema_farmacia.model.clasesdata.CategoriasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Categoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PantallaCategoriasController {

    @FXML private Button btnAgregar;
    @FXML private Button btnListar;
    @FXML private VBox areaSubpagina;
    @FXML private GridPane formularioAgregar;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private Button btnAceptar;

    // Referencia al modelo de colección
    private CategoriasDB categoriasDB;

    // Inyectada por el controlador principal (método set)
    public void setCategoriasDB(CategoriasDB categoriasDB) {
        this.categoriasDB = categoriasDB;
    }

    @FXML
    public void initialize() {
        btnAgregar.setOnAction(e -> mostrarAgregar());
        btnListar.setOnAction(e -> mostrarListar());
        btnAceptar.setOnAction(e -> agregarCategoria());
        mostrarAgregar();
    }

    /** --- AGREGAR --- **/
    private void mostrarAgregar() {
        areaSubpagina.getChildren().clear();
        areaSubpagina.getChildren().add(formularioAgregar);
    }

    private void agregarCategoria() {
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        if (nombre.isEmpty()) {
            mostrarMensaje("El nombre de la categoría no puede estar vacío.");
            return;
        }
        Categoria nueva = new Categoria(nombre, descripcion);
        categoriasDB.agregarCategoria(nueva);
        mostrarMensaje("Categoría agregada:\n" + nombre + "\n" + descripcion);
        txtNombre.clear();
        txtDescripcion.clear();
    }

    /** --- LISTAR Y BUSCAR --- **/
    private void mostrarListar() {
        areaSubpagina.getChildren().clear();

        // Buscador
        HBox buscador = new HBox(12);
        Label lblBuscar = new Label("Buscar:");
        TextField txtBuscar = new TextField();
        Button btnBuscar = new Button("Buscar");
        buscador.getChildren().addAll(lblBuscar, txtBuscar, btnBuscar);

        TableView<Categoria> tabla = new TableView<>();
        tabla.setPrefHeight(220);

        TableColumn<Categoria, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoriaNombre()));

        TableColumn<Categoria, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));

        TableColumn<Categoria, Void> colOpciones = new TableColumn<>("Opciones");
        colOpciones.setCellFactory(tc -> new TableCell<>() {
            private final Button btnModificar = new Button("Modificar");
            private final Button btnEliminar = new Button("Eliminar");
            {
                btnModificar.setOnAction(e -> mostrarModificar(getTableView().getItems().get(getIndex())));
                btnEliminar.setOnAction(e -> mostrarEliminar(getTableView().getItems().get(getIndex())));
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : new HBox(6, btnModificar, btnEliminar));
            }
        });

        tabla.getColumns().addAll(colNombre, colDescripcion, colOpciones);

        // Obtiene la lista actual y la carga
        List<Categoria> todas = new ArrayList<>(categoriasDB.getListaCategorias().values());
        tabla.setItems(FXCollections.observableArrayList(todas));

        // Filtro por nombre (insensible a mayúsculas/minúsculas)
        btnBuscar.setOnAction(e -> {
            String filtro = txtBuscar.getText().trim().toLowerCase();
            if (filtro.isEmpty()) {
                tabla.setItems(FXCollections.observableArrayList(categoriasDB.getListaCategorias().values()));
            } else {
                ObservableList<Categoria> filtradas = FXCollections.observableArrayList();
                for (Categoria cat : categoriasDB.getListaCategorias().values()) {
                    if (cat.getCategoriaNombre().toLowerCase().contains(filtro)) {
                        filtradas.add(cat);
                    }
                }
                tabla.setItems(filtradas);
            }
        });

        VBox listado = new VBox(12, buscador, tabla);
        listado.setPadding(new Insets(10));
        areaSubpagina.getChildren().add(listado);
    }

    /** --- MODIFICAR --- **/
    private void mostrarModificar(Categoria cat) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modificar Categoría");

        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(12); grid.setPadding(new Insets(20));
        TextField nuevoNombre = new TextField(cat.getCategoriaNombre());
        TextArea nuevaDescripcion = new TextArea(cat.getDescripcion());

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nuevoNombre, 1, 0);
        grid.add(new Label("Descripción:"), 0, 1);
        grid.add(nuevaDescripcion, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> bt);
        dialog.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                String nombreViejo = cat.getCategoriaNombre();
                String nombre = nuevoNombre.getText().trim();
                String desc = nuevaDescripcion.getText().trim();
                if (!nombre.isEmpty()) cat.setCategoriaNombre(nombre);
                if (!desc.isEmpty()) cat.setDescripcion(desc);

                categoriasDB.modificarCategoria(nombreViejo, cat);
                mostrarListar();
            }
        });
    }

    /** --- ELIMINAR --- **/
    private void mostrarEliminar(Categoria cat) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("¿Seguro que quieres eliminar?");
        alerta.setContentText("Categoría: " + cat.getCategoriaNombre());
        alerta.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                categoriasDB.eliminarCategoria(cat.getCategoriaNombre());
                mostrarListar();
            }
        });
    }

    /** --- MENSAJE --- **/
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje del sistema");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
