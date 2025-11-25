package com.example.sistema_farmacia.controller.controladores;

import com.example.sistema_farmacia.model.clasesdata.CategoriasDB;
import com.example.sistema_farmacia.model.clasesdata.ProductosDB;
import com.example.sistema_farmacia.model.clasesplantillas.Categoria;
import com.example.sistema_farmacia.model.clasesplantillas.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PantallaProductosController {

    @FXML private Button btnAgregar, btnListar;
    @FXML private VBox areaSubpagina;

    private ProductosDB productosDB;
    private CategoriasDB categoriasDB;

    // Referencias para "Agregar"
    private GridPane formularioAgregarProducto;
    private TextField txtNombre, txtDescripcion, txtCodigo, txtPrecio, txtPrecioVenta, txtUnidades;
    private DatePicker dpCaducidad;
    private VBox boxCheckCategorias;
    private Button btnAceptar;

    // Referencias para "Listar"
    private VBox boxListado;
    private TableView<Producto> tablaProductos;
    private TextField txtBuscarCodigo;

    public void setProductosDB(ProductosDB productosDB) { this.productosDB = productosDB; }
    public void setCategoriasDB(CategoriasDB categoriasDB) { this.categoriasDB = categoriasDB; }

    @FXML
    public void initialize() {
        btnAgregar.setOnAction(e -> mostrarAgregar());
        btnListar.setOnAction(e -> mostrarListar());
        mostrarAgregar(); // Por defecto muestra "Agregar"
    }

    //------------ SUBPÁGINA AGREGAR --------------//
    private void mostrarAgregar() {
        areaSubpagina.getChildren().clear();

        if (formularioAgregarProducto == null) {
            formularioAgregarProducto = new GridPane();
            formularioAgregarProducto.setHgap(12); formularioAgregarProducto.setVgap(11);

            txtNombre = new TextField();
            txtDescripcion = new TextField();
            txtCodigo = new TextField();
            dpCaducidad = new DatePicker();
            txtPrecio = new TextField();
            txtPrecioVenta = new TextField();
            txtUnidades = new TextField();
            boxCheckCategorias = new VBox(5);
            btnAceptar = new Button("Aceptar");

            formularioAgregarProducto.addRow(0, new Label("Nombre:"), txtNombre);
            formularioAgregarProducto.addRow(1, new Label("Descripción:"), txtDescripcion);
            formularioAgregarProducto.addRow(2, new Label("Código:"), txtCodigo);
            formularioAgregarProducto.addRow(3, new Label("Fecha Caducidad:"), dpCaducidad);
            formularioAgregarProducto.addRow(4, new Label("Precio normal:"), txtPrecio);
            formularioAgregarProducto.addRow(5, new Label("Precio venta especial:"), txtPrecioVenta);
            formularioAgregarProducto.addRow(6, new Label("Unidades disponibles:"), txtUnidades);
            formularioAgregarProducto.addRow(7, new Label("Categorías:"), boxCheckCategorias);
            formularioAgregarProducto.addRow(8, new Label(), btnAceptar);
        }

        cargarCategoriasCheck();

        btnAceptar.setOnAction(e -> agregarProductoCheck());
        areaSubpagina.getChildren().setAll(formularioAgregarProducto);
    }

    // Carga los checkboxes de categorías
    private void cargarCategoriasCheck() {
        if (categoriasDB != null && boxCheckCategorias != null) {
            boxCheckCategorias.getChildren().clear();
            for (Categoria categoria : categoriasDB.getCategoriasParaProductos()) {
                CheckBox check = new CheckBox(categoria.getCategoriaNombre());
                check.setUserData(categoria);
                boxCheckCategorias.getChildren().add(check);
            }
        }
    }

    // Agrega el producto tomado de los checkboxes
    private void agregarProductoCheck() {
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String codigo = txtCodigo.getText().trim();
        LocalDate caducidad = dpCaducidad.getValue();
        double precio, precioVenta;
        int unidades;

        try { precio = Double.parseDouble(txtPrecio.getText().trim()); }
        catch (Exception ex) { mostrarMensaje("Precio normal inválido"); return; }
        try { precioVenta = Double.parseDouble(txtPrecioVenta.getText().trim()); }
        catch (Exception ex) { mostrarMensaje("Precio venta especial inválido"); return; }
        try { unidades = Integer.parseInt(txtUnidades.getText().trim()); }
        catch (Exception ex) { mostrarMensaje("Unidades debe ser entero"); return; }

        if (nombre.isEmpty() || descripcion.isEmpty() || codigo.isEmpty() || caducidad == null) {
            mostrarMensaje("Completa todos los campos obligatorios.");
            return;
        }
        if (caducidad.isBefore(LocalDate.now())) {
            mostrarMensaje("La fecha de caducidad debe ser mayor a la actual.");
            return;
        }

        ArrayList<Categoria> categoriasProducto = new ArrayList<>();
        for (javafx.scene.Node nodo : boxCheckCategorias.getChildren()) {
            if (nodo instanceof CheckBox cb && cb.isSelected()) {
                categoriasProducto.add((Categoria) cb.getUserData());
            }
        }
        if (categoriasProducto.isEmpty() && categoriasDB != null) {
            categoriasProducto.add(categoriasDB.getCategoriaDefault());
        }

        Producto nuevo = new Producto(nombre, descripcion, caducidad, precio, precioVenta, unidades, codigo);
        nuevo.setCategoria(categoriasProducto);

        productosDB.agregarProducto(nuevo);

        mostrarMensaje("Producto agregado correctamente.");
        limpiarFormularioCheck();
        cargarCategoriasCheck();
    }

    private void limpiarFormularioCheck() {
        txtNombre.clear(); txtDescripcion.clear(); txtCodigo.clear(); dpCaducidad.setValue(null);
        txtPrecio.clear(); txtPrecioVenta.clear(); txtUnidades.clear();
        for (javafx.scene.Node nodo : boxCheckCategorias.getChildren()) {
            if (nodo instanceof CheckBox cb) cb.setSelected(false);
        }
    }

    //------------ SUBPÁGINA LISTAR --------------//
    private void mostrarListar() {
        areaSubpagina.getChildren().clear();

        if (boxListado == null) {
            boxListado = new VBox(12);

            HBox buscador = new HBox(8);
            txtBuscarCodigo = new TextField();
            txtBuscarCodigo.setPromptText("Código");
            Button btnBuscar = new Button("Buscar");
            buscador.getChildren().addAll(new Label("Buscar:"), txtBuscarCodigo, btnBuscar);

            tablaProductos = new TableView<>();
            tablaProductos.setPrefHeight(220);

            TableColumn<Producto, String> colCodigo = new TableColumn<>("Código");
            colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

            TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
            colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

            TableColumn<Producto, String> colDescripcion = new TableColumn<>("Descripción");
            colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));

            TableColumn<Producto, String> colCategorias = new TableColumn<>("Categorías");
            colCategorias.setCellValueFactory(data -> {
                ArrayList<Categoria> cats = data.getValue().getCategoria();
                String s = cats.stream().map(Categoria::getCategoriaNombre).collect(Collectors.joining(", "));
                return new SimpleStringProperty(s);
            });

            TableColumn<Producto, String> colPrecioVenta = new TableColumn<>("Precio Venta");
            colPrecioVenta.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPrecioVenta())));

            TableColumn<Producto, String> colStock = new TableColumn<>("Stock");
            colStock.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUnidadesExi())));

            // --- OPCIONES original ---
            TableColumn<Producto, Void> colOpciones = new TableColumn<>("Opciones");
            colOpciones.setCellFactory(tc -> new TableCell<>() {
                private final Button btnModificar = new Button("Modificar");
                private final Button btnEliminar = new Button("Eliminar");
                {
                    btnModificar.setOnAction(e -> mostrarModificar(getTableView().getItems().get(getIndex())));
                    btnEliminar.setOnAction(e -> mostrarEliminar(getTableView().getItems().get(getIndex())));
                    btnModificar.setPadding(new Insets(2,8,2,8));
                    btnEliminar.setPadding(new Insets(2,8,2,8));
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : new HBox(6, btnModificar, btnEliminar));
                }
            });

            tablaProductos.getColumns().addAll(
                    colCodigo, colNombre, colDescripcion, colCategorias,
                    colPrecioVenta, colStock, colOpciones
            );

            btnBuscar.setOnAction(e -> buscarProducto());
            txtBuscarCodigo.setOnAction(e -> buscarProducto());

            boxListado.getChildren().addAll(buscador, tablaProductos);
        }

        refrescarTabla();
        areaSubpagina.getChildren().setAll(boxListado);
    }


    private void buscarProducto() {
        if (productosDB == null) return;
        String filtro = txtBuscarCodigo.getText().trim().toLowerCase();
        ObservableList<Producto> todos = FXCollections.observableArrayList(productosDB.getListaProductos().values());
        ObservableList<Producto> filtrados = FXCollections.observableArrayList();
        if (!filtro.isEmpty()) {
            for (Producto pr : todos) {
                if (pr.getCodigo().toLowerCase().contains(filtro))
                    filtrados.add(pr);
            }
        }
        tablaProductos.setItems(filtro.isEmpty() ? todos : filtrados);
    }

    private void refrescarTabla() {
        if (productosDB != null && tablaProductos != null)
            tablaProductos.setItems(FXCollections.observableArrayList(productosDB.getListaProductos().values()));
    }

    private void mostrarModificar(Producto producto) {
        Dialog<ButtonType> dialogo = new Dialog<>();
        dialogo.setTitle("Modificar Producto");

        GridPane grid = new GridPane();
        grid.setVgap(8); grid.setHgap(10); grid.setPadding(new Insets(15));

        TextField campoNombre = new TextField(producto.getNombre());
        TextField campoDescripcion = new TextField(producto.getDescripcion());
        TextField campoPrecio = new TextField(String.valueOf(producto.getPrecio()));
        TextField campoPrecioVenta = new TextField(String.valueOf(producto.getPrecioVenta()));
        TextField campoCodigo = new TextField(producto.getCodigo());
        DatePicker campoCaducidad = new DatePicker(producto.getFechaCaducidad());
        TextField campoUnidades = new TextField(String.valueOf(producto.getUnidadesExi()));

        Label labelCategorias = new Label(
                producto.getCategoria().stream().map(Categoria::getCategoriaNombre).collect(Collectors.joining(", "))
        );

        grid.add(new Label("Nombre:"), 0, 0); grid.add(campoNombre, 1, 0);
        grid.add(new Label("Descripción:"), 0, 1); grid.add(campoDescripcion, 1, 1);
        grid.add(new Label("Código:"), 0, 2); grid.add(campoCodigo, 1, 2);
        grid.add(new Label("Fecha Caducidad:"), 0, 3); grid.add(campoCaducidad, 1, 3);
        grid.add(new Label("Precio normal:"), 0, 4); grid.add(campoPrecio, 1, 4);
        grid.add(new Label("Precio venta:"), 0, 5); grid.add(campoPrecioVenta, 1, 5);
        grid.add(new Label("Unidades:"), 0, 6); grid.add(campoUnidades, 1, 6);
        grid.add(new Label("Categorías actuales:"), 0, 7); grid.add(labelCategorias, 1, 7);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                boolean cambiado = false;
                if (!campoNombre.getText().trim().equals(producto.getNombre())) {
                    producto.setNombre(campoNombre.getText().trim()); cambiado = true;
                }
                if (!campoDescripcion.getText().trim().equals(producto.getDescripcion())) {
                    producto.setDescripcion(campoDescripcion.getText().trim()); cambiado = true;
                }
                if (!campoCodigo.getText().trim().equals(producto.getCodigo())) {
                    productosDB.eliminarProducto(producto.getCodigo());
                    producto.setCodigo(campoCodigo.getText().trim()); cambiado = true;
                }
                try {
                    double precio = Double.parseDouble(campoPrecio.getText().trim());
                    if (precio != producto.getPrecio()) { producto.setPrecio(precio); cambiado = true; }
                } catch (NumberFormatException ignored) {}
                try {
                    double precioVenta = Double.parseDouble(campoPrecioVenta.getText().trim());
                    if (precioVenta != producto.getPrecioVenta()) { producto.setPrecioVenta(precioVenta); cambiado = true; }
                } catch (NumberFormatException ignored) {}
                try {
                    int unidades = Integer.parseInt(campoUnidades.getText().trim());
                    if (unidades != producto.getUnidadesExi()) { producto.setUnidadesExi(unidades); cambiado = true; }
                } catch (NumberFormatException ignored) {}
                if (campoCaducidad.getValue() != null && !campoCaducidad.getValue().equals(producto.getFechaCaducidad())) {
                    producto.setFechaCaducidad(campoCaducidad.getValue()); cambiado = true;
                }
                if (cambiado) {
                    productosDB.agregarProducto(producto);
                    refrescarTabla();
                }
            }
        });
    }

    private void mostrarEliminar(Producto producto) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar Producto");
        alerta.setHeaderText("¿Seguro que quieres eliminar?");
        alerta.setContentText("Producto: " + producto.getNombre());
        alerta.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                productosDB.eliminarProducto(producto.getCodigo());
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
