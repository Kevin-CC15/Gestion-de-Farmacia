package com.example.sistema_farmacia.controller.controladores;

import com.example.sistema_farmacia.model.clasesdata.ProductosDB;
import com.example.sistema_farmacia.model.clasesdata.VentasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Producto;
import com.example.sistema_farmacia.model.clasesreportes.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PantallaReportesController {

    @FXML private DatePicker dateDiario, dateSemanal;
    @FXML private ComboBox<String> mesesCombo;
    @FXML private Spinner<Integer> anioMensual, anioAnual;
    @FXML private Button btnDiario, btnSemanal, btnMensual, btnAnual, btnInventario;
    @FXML private TextArea txtReporte;

    private VentasDB ventasDB;
    private ProductosDB productosDB;

    public void setVentasDB(VentasDB ventasDB) { this.ventasDB = ventasDB; }
    public void setProductosDB(ProductosDB productosDB) { this.productosDB = productosDB; }

    @FXML
    public void initialize() {
        mesesCombo.getItems().addAll(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );
        mesesCombo.getSelectionModel().select(LocalDate.now().getMonthValue() - 1);

        int currentYear = LocalDate.now().getYear();
        anioMensual.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, currentYear, currentYear)
        );
        anioAnual.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, currentYear, currentYear)
        );

        btnDiario.setOnAction(e -> generarReporteDiario());
        btnSemanal.setOnAction(e -> generarReporteSemanal());
        btnMensual.setOnAction(e -> generarReporteMensual());
        btnAnual.setOnAction(e -> generarReporteAnual());
        btnInventario.setOnAction(e -> generarReporteInventario());
    }

    private void generarReporteDiario() {
        if (ventasDB == null) { txtReporte.setText("Error: No hay BD de ventas."); return; }
        LocalDate fecha = dateDiario.getValue() != null ? dateDiario.getValue() : LocalDate.now();
        ReporteGenerador generador = new ReporteGenerador(ventasDB, productosDB);
        ReporteDiario reporte = generador.generarReporteVentasDiario(fecha);

        double totalVenta = reporte.sacarTotalVenta();
        double gananciaTotal = reporte.sacarTotalGanacia();
        int cantidadProductos = reporte.sacarArrayListVentas().stream()
                .mapToInt(v -> v.getVenta().size()).sum();

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE DIARIO\nFecha: ").append(fecha).append("\n\n")
                .append("Total ventas: $").append(String.format("%.2f", totalVenta)).append("\n")
                .append("Ganancia total: $").append(String.format("%.2f", gananciaTotal)).append("\n")
                .append("Cantidad de productos vendidos: ").append(cantidadProductos).append("\n\n")
                .append(reporte.generarReporte());
        txtReporte.setText(sb.toString());
    }

    private void generarReporteSemanal() {
        if (ventasDB == null) { txtReporte.setText("Error: No hay BD de ventas."); return; }
        LocalDate ini = dateSemanal.getValue();
        if (ini == null) ini = LocalDate.now().with(DayOfWeek.MONDAY);
        ReporteGenerador generador = new ReporteGenerador(ventasDB, productosDB);
        ReporteSemanal reporte = generador.generarReporteVentasSemanal(ini);

        double totalVenta = reporte.sacarTotalVenta();
        double gananciaTotal = reporte.sacarTotalGanacia();
        int cantidadProductos = reporte.sacarArrayListVentas().stream()
                .mapToInt(v -> v.getVenta().size()).sum();

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE SEMANAL\nSemana inicio: ").append(ini).append("\n\n")
                .append("Total ventas: $").append(String.format("%.2f", totalVenta)).append("\n")
                .append("Ganancia total: $").append(String.format("%.2f", gananciaTotal)).append("\n")
                .append("Cantidad de productos vendidos: ").append(cantidadProductos).append("\n\n")
                .append(reporte.generarReporte());
        txtReporte.setText(sb.toString());
    }

    private void generarReporteMensual() {
        if (ventasDB == null) { txtReporte.setText("Error: No hay BD de ventas."); return; }
        Integer anio = anioMensual.getValue();
        int mes = mesesCombo.getSelectionModel().getSelectedIndex() + 1;
        if (anio == null || mes < 1) {
            txtReporte.setText("Selecciona año y mes.");
            return;
        }
        ReporteGenerador generador = new ReporteGenerador(ventasDB, productosDB);
        ReporteMensual reporte = generador.generarReporteVentasMensual(anio, mes);

        double totalVenta = reporte.sacarTotalVenta();
        int cantidadProductos = reporte.sacarArrayListVentas().stream()
                .mapToInt(v -> v.getVenta().size()).sum();

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE MENSUAL\nAño: ").append(anio).append(" Mes: ").append(mes).append("\n\n")
                .append("Total ventas: $").append(String.format("%.2f", totalVenta)).append("\n")
                .append("Cantidad de productos vendidos: ").append(cantidadProductos).append("\n\n")
                .append(reporte.generarReporte());
        txtReporte.setText(sb.toString());
    }

    private void generarReporteAnual() {
        if (ventasDB == null) { txtReporte.setText("Error: No hay BD de ventas."); return; }
        Integer anio = anioAnual.getValue();
        if (anio == null) {
            txtReporte.setText("Selecciona un año.");
            return;
        }
        ReporteGenerador generador = new ReporteGenerador(ventasDB, productosDB);
        ReporteAnual reporte = generador.generarReporteVentasAnual(anio);

        double totalVenta = reporte.sacarTotalVenta();
        double gananciaTotal = reporte.sacarTotalGanacia();
        int cantidadProductos = reporte.sacarArrayListVentas().stream()
                .mapToInt(v -> v.getVenta().size()).sum();

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE ANUAL\nAño: ").append(anio).append("\n\n")
                .append("Total ventas: $").append(String.format("%.2f", totalVenta)).append("\n")
                .append("Ganancia total: $").append(String.format("%.2f", gananciaTotal)).append("\n")
                .append("Cantidad de productos vendidos: ").append(cantidadProductos).append("\n\n")
                .append(reporte.generarReporte());
        txtReporte.setText(sb.toString());
    }

    private void generarReporteInventario() {
        if (productosDB == null) {
            txtReporte.setText("Error: No hay BD de productos.");
            return;
        }
        ReporteGenerador generador = new ReporteGenerador(ventasDB, productosDB);
        ReporteInventario reporte = generador.generarReporteInventario();
        LocalDate ahora = LocalDate.now();

        List<Producto> sinStock = reporte.listarProductosAgotar();
        List<Producto> porCaducar = reporte.listarProductosCaducar();
        List<Producto> sinCategoria = reporte.listarProductosSinCategoria();

        StringBuilder sb = new StringBuilder();
        sb.append("========== REPORTE DE INVENTARIO ==========\n\n");

        sb.append("PRODUCTOS SIN STOCK:\n");
        if (sinStock.isEmpty()) sb.append("  * Ninguno *\n");
        else sinStock.forEach(p ->
                sb.append("  - ").append(p.getCodigo())
                        .append(" | ").append(p.getNombre())
                        .append(" | Stock: 0\n")
        );

        sb.append("\nPRODUCTOS POR CADUCAR (próx. 30 días):\n");
        if (porCaducar.isEmpty()) sb.append("  * Ninguno *\n");
        else porCaducar.forEach(p ->
                sb.append("  - ").append(p.getCodigo())
                        .append(" | ").append(p.getNombre())
                        .append(" | Vence: ").append(p.getFechaCaducidad())
                        .append(" | Unidades: ").append(p.getUnidadesExi())
                        .append("\n")
        );

        sb.append("\nPRODUCTOS SIN CATEGORÍA:\n");
        if (sinCategoria.isEmpty()) sb.append("  * Ninguno *\n");
        else sinCategoria.forEach(p ->
                sb.append("  - ").append(p.getCodigo())
                        .append(" | ").append(p.getNombre())
                        .append(" | Stock: ").append(p.getUnidadesExi()).append("\n")
        );

        sb.append("\n===========================================\n");
        txtReporte.setText(sb.toString());
    }
}
