package model.clasesreportes;

import model.clasesdata.VentasDB;
import model.clasesdata.ProductosDB;
import java.time.LocalDate;

public class ReporteGenerador {
    private VentasDB ventasDB;
    private ProductosDB productosDB;

    // Constructor ReporteGenerador(ventas : VentasDB, productos : ProductosDB)
    public ReporteGenerador(VentasDB ventas, ProductosDB productos) {
        this.ventasDB = ventas;
        this.productosDB = productos;
    }


    public ReporteDiario generarReporteVentasDiario(LocalDate fecha) {
        return new ReporteDiario(ventasDB, fecha);
    }

    public ReporteSemanal generarReporteVentasSemanal(LocalDate fechaInicio) {
        return new ReporteSemanal(ventasDB, fechaInicio);
    }

    public ReporteMensual generarReporteVentasMensual(int anio, int mes) {
        return new ReporteMensual(ventasDB, anio, mes);
    }

    public ReporteAnual generarReporteVentasAnual(int anio) {
        return new ReporteAnual(ventasDB, anio);
    }

    public ReporteInventario generarReporteInventario() {
        return new ReporteInventario(productosDB);
    }
}