package com.example.sistemafarmacia.Vista.navegacion;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.util.function.Consumer;

/**
 * Representa el menú superior que permite navegar entre secciones principales.
 */
public class MenuPrincipal extends HBox {
    public Button btnPrincipal, btnInventario, btnCategorias, btnReportes, btnSalir;
    private Consumer<String> manejadorNavegacion;



    public MenuPrincipal() {
        btnPrincipal = new Button("Principal");
        btnInventario = new Button("Inventario");
        btnCategorias = new Button("Categorías");
        btnReportes = new Button("Reportes");
        btnSalir = new Button("Salir");

        this.getChildren().addAll(btnPrincipal, btnInventario, btnCategorias, btnReportes, btnSalir);
        this.setSpacing(8);

        btnPrincipal.setOnAction(e -> notificar("principal"));
        btnInventario.setOnAction(e -> notificar("inventario"));
        btnCategorias.setOnAction(e -> notificar("categorias"));
        btnReportes.setOnAction(e -> notificar("reportes"));
        btnSalir.setOnAction(e -> notificar("salir"));

        this.setStyle("-fx-background-color: lightblue;");
        btnPrincipal.setPrefWidth(120);
        btnInventario.setPrefWidth(120);
        btnCategorias.setPrefWidth(120);
        btnReportes.setPrefWidth(120);
        btnSalir.setPrefWidth(120);
    }

    public void setManejadorNavegacion(Consumer<String> manejador) {
        this.manejadorNavegacion = manejador;
    }
    private void notificar(String destino) {
        if (manejadorNavegacion != null) manejadorNavegacion.accept(destino);
    }
}
