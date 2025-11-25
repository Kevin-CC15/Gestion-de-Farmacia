package com.example.sistema_farmacia.controller.controladores;

import com.example.sistema_farmacia.model.clasesdata.ClientesDB;
import com.example.sistema_farmacia.model.clasesplantillas.Cliente;
import com.example.sistema_farmacia.model.clasesplantillas.TipoCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class PantallaClientesController {

    @FXML private Button btnAgregar;
    @FXML private Button btnListar;
    @FXML private VBox areaSubpagina;
    @FXML private GridPane formularioAgregar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtNumeroContacto;
    @FXML private CheckBox chkClienteAmigo;
    @FXML private ComboBox<TipoCliente> cmbTipoCliente;
    @FXML private TextField txtDescuento;
    @FXML private Button btnAceptar;

    // Elementos para la subpágina listar
    private TableView<Cliente> tablaClientes;
    private TextField txtBuscarCliente;
    private Button btnBuscarCliente;

    private ClientesDB clientesDB;

    public void setClientesDB(ClientesDB clientesDB) {
        this.clientesDB = clientesDB;
    }

    @FXML
    public void initialize() {
        cmbTipoCliente.getItems().setAll(TipoCliente.values());
        btnAceptar.setOnAction(e -> agregarCliente());
        btnAgregar.setOnAction(e -> mostrarAgregar());
        btnListar.setOnAction(e -> mostrarListar());
        mostrarAgregar();
    }

    // --- Agregar Cliente ---
    private void mostrarAgregar() {
        areaSubpagina.getChildren().clear();
        areaSubpagina.getChildren().add(formularioAgregar);
    }

    private void agregarCliente() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String contacto = txtNumeroContacto.getText().trim();
        boolean esAmigo = chkClienteAmigo.isSelected();
        TipoCliente tipo = cmbTipoCliente.getValue();
        double descuento = 0;
        try {
            if (!txtDescuento.getText().isEmpty()) {
                descuento = Double.parseDouble(txtDescuento.getText().trim());
            }
        } catch (NumberFormatException ex) {
            mostrarMensaje("El porcentaje de descuento debe ser numérico.");
            return;
        }
        if (nombre.isEmpty() || direccion.isEmpty() || contacto.isEmpty() || tipo == null) {
            mostrarMensaje("Completa todos los campos obligatorios.");
            return;
        }

        Cliente nuevo = new Cliente(nombre, direccion, contacto, esAmigo);
        nuevo.setTipoCliente(tipo);
        nuevo.setPorcentajeDescuento(descuento);
        clientesDB.agregarCliente(nuevo);

        mostrarMensaje("Cliente agregado correctamente.");
        txtNombre.clear(); txtDireccion.clear(); txtNumeroContacto.clear();
        chkClienteAmigo.setSelected(false); cmbTipoCliente.setValue(null); txtDescuento.clear();
    }

    // --- Listar Clientes ---
    private void mostrarListar() {
        areaSubpagina.getChildren().clear();

        HBox buscador = new HBox(8);
        txtBuscarCliente = new TextField();
        txtBuscarCliente.setPromptText("Nombre (buscar insensible a mayúsculas)");
        btnBuscarCliente = new Button("Buscar");
        buscador.getChildren().addAll(new Label("Buscar:"), txtBuscarCliente, btnBuscarCliente);

        tablaClientes = new TableView<>();
        tablaClientes.setPrefHeight(220);

        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Cliente, String> colNumero = new TableColumn<>("Número de contacto");
        colNumero.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroContacto()));

        TableColumn<Cliente, String> colTipo = new TableColumn<>("Tipo de cliente");
        colTipo.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTipoCliente() != null
                        ? data.getValue().getTipoCliente().getTipo()
                        : ""
        ));

        TableColumn<Cliente, Void> colOpciones = new TableColumn<>("Opciones");
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

        tablaClientes.getColumns().addAll(colNombre, colNumero, colTipo, colOpciones);

        btnBuscarCliente.setOnAction(e -> buscarCliente());
        txtBuscarCliente.setOnAction(e -> buscarCliente());

        refrescarTabla();

        VBox listado = new VBox(12, buscador, tablaClientes);
        listado.setPadding(new Insets(10));
        areaSubpagina.getChildren().add(listado);
    }

    private void refrescarTabla() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clientesDB.getListaClientes().values());
        tablaClientes.setItems(clientes);
    }

    private void buscarCliente() {
        String filtro = txtBuscarCliente.getText().trim().toLowerCase();
        if (filtro.isEmpty()) {
            refrescarTabla();
            return;
        }
        ArrayList<Cliente> filtrados = new ArrayList<>();
        for (Cliente cli : clientesDB.getListaClientes().values()) {
            if (cli.getNombre().toLowerCase().contains(filtro)) {
                filtrados.add(cli);
            }
        }
        tablaClientes.setItems(FXCollections.observableArrayList(filtrados));
    }

    private void mostrarModificar(Cliente cliente) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modificar Cliente");

        GridPane grid = new GridPane();
        grid.setVgap(8); grid.setHgap(10); grid.setPadding(new Insets(15));
        TextField campoNombre = new TextField(cliente.getNombre());
        TextField campoDir = new TextField(cliente.getDireccion());
        TextField campoNum = new TextField(cliente.getNumeroContacto());
        ComboBox<TipoCliente> comboTipo = new ComboBox<>(FXCollections.observableArrayList(TipoCliente.values()));
        comboTipo.setValue(cliente.getTipoCliente());
        CheckBox chkAmigo = new CheckBox("Cliente amigo"); chkAmigo.setSelected(cliente.getEsClienteAmigo());
        TextField campoDesc = new TextField(String.valueOf(cliente.getPorcentajeDescuento()));

        grid.add(new Label("Nombre:"), 0, 0); grid.add(campoNombre, 1, 0);
        grid.add(new Label("Dirección:"), 0, 1); grid.add(campoDir, 1, 1);
        grid.add(new Label("Número de contacto:"), 0, 2); grid.add(campoNum, 1, 2);
        grid.add(new Label("Tipo de cliente:"), 0, 3); grid.add(comboTipo, 1, 3);
        grid.add(chkAmigo, 1, 4);
        grid.add(new Label("Descuento:"), 0, 5); grid.add(campoDesc, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> bt);
        dialog.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                boolean cambiado = false;
                if (!campoNombre.getText().trim().equals(cliente.getNombre())) {
                    cliente.setNombre(campoNombre.getText().trim()); cambiado = true;
                }
                if (!campoDir.getText().trim().equals(cliente.getDireccion())) {
                    cliente.setDireccion(campoDir.getText().trim()); cambiado = true;
                }
                if (!campoNum.getText().trim().equals(cliente.getNumeroContacto())) {
                    cliente.setNumeroContacto(campoNum.getText().trim()); cambiado = true;
                }
                if (comboTipo.getValue() != cliente.getTipoCliente()) {
                    cliente.setTipoCliente(comboTipo.getValue()); cambiado = true;
                }
                if (chkAmigo.isSelected() != cliente.getEsClienteAmigo()) {
                    cliente.setEsClienteAmigo(chkAmigo.isSelected()); cambiado = true;
                }
                try {
                    double descuento = Double.parseDouble(campoDesc.getText().trim());
                    if (descuento != cliente.getPorcentajeDescuento()) {
                        cliente.setPorcentajeDescuento(descuento); cambiado = true;
                    }
                } catch (NumberFormatException ignored) {}
                if (cambiado) {
                    clientesDB.modificarCliente(cliente.getNombre(), cliente);
                }
                refrescarTabla();
            }
        });
    }

    private void mostrarEliminar(Cliente cliente) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar Cliente");
        alerta.setHeaderText("¿Seguro que quieres eliminar?");
        alerta.setContentText("Cliente: " + cliente.getNombre());
        alerta.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                clientesDB.eliminarCliente(cliente.getNombre());
                refrescarTabla();
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
