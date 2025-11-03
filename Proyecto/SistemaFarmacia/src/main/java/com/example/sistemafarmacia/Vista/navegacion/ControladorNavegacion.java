package com.example.sistemafarmacia.Vista.navegacion;

import com.example.sistemafarmacia.Vista.categoria.PantallaCategorias;
import com.example.sistemafarmacia.Vista.inventario.PantallaInventario;
import com.example.sistemafarmacia.Vista.principal.PantallaPrincipal;
import com.example.sistemafarmacia.Vista.reportes.PantallaReportes;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona la navegación (mapa de pantallas y el cambio entre ellas).
 */
public class ControladorNavegacion {
    private Stage ventanaPrincipal;
    private PantallaBase pantallaActual;
    private MenuPrincipal menuPrincipal;
    private Map<String, PantallaBase> pantallas;

    public ControladorNavegacion(Stage ventana) {
        this.ventanaPrincipal = ventana;
        this.menuPrincipal = new MenuPrincipal();
        this.pantallas = new HashMap<>();

        // Puedes agregar tantas pantallas como desees
        pantallas.put("principal", new PantallaPrincipal(menuPrincipal));
        pantallas.put("inventario", new PantallaInventario(menuPrincipal));
        pantallas.put("categorias", new PantallaCategorias(menuPrincipal));
        pantallas.put("reportes", new PantallaReportes(menuPrincipal));

        menuPrincipal.setManejadorNavegacion(this::cambiarPantalla);
        cambiarPantalla("principal");
        ventanaPrincipal.setTitle("Sistema de Farmacia");
        ventanaPrincipal.setOnCloseRequest(e -> salirAplicacion());
    }

    public void cambiarPantalla(String nombre) {
        if (nombre.equals("salir")) {
            salirAplicacion();
            return;
        }
        PantallaBase nueva = pantallas.get(nombre);
        if (nueva != null) {
            this.pantallaActual = nueva;
            pantallaActual.mostrar();
            ventanaPrincipal.setScene(new Scene(pantallaActual, 900, 600));
        }
    }

    public void salirAplicacion() {
        ventanaPrincipal.close();
    }
}
