package com.example.sistema_farmacia.model.clasesreportes;

import com.example.sistema_farmacia.model.clasesdata.VentasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Venta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ReporteVentas {
    protected VentasDB ventasDB;

    public ReporteVentas(VentasDB ventasDB) {
        this.ventasDB = ventasDB;
    }

    // Retorna todas las ventas (sin filtrar)
    public ArrayList<Venta> sacarArrayListVentas() {
        Map<String, Venta> mapaVentas = this.ventasDB.getListaVentas();
        Collection<Venta> valores = mapaVentas.values();
        return new ArrayList<>(valores);
    }

    public String generarReporte() {
        return "Reporte base de ventas (sobrescribir en hijos)";
    }

    public double sacarTotalVenta() {
        return sacarArrayListVentas().stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    public double sacarTotalGanacia() {
        return sacarArrayListVentas().stream()
                .mapToDouble(Venta::getTotal) // Debes tener 'getGanancia' en Venta
                .sum();
    }

    public void mostrarInfoVentas() {
        sacarArrayListVentas().forEach(System.out::println);
    }
}
