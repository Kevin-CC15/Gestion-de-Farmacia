package com.example.sistema_farmacia.model.clasesreportes;

import com.example.sistema_farmacia.model.clasesdata.VentasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Producto;
import com.example.sistema_farmacia.model.clasesplantillas.Venta;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReporteDiario extends ReporteVentas {
    private final LocalDate fecha;

    public ReporteDiario(VentasDB ventasDB, LocalDate fecha) {
        super(ventasDB);
        this.fecha = fecha;
    }

    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ventas del día ").append(fecha).append(":\n\n");
        sb.append(String.format("%-10s %-20s %-10s %-10s\n", "ID", "Cliente", "Total", "Fecha"));
        sb.append("---------------------------------------------------------\n");

        int num = 1;
        for (Venta v : sacarArrayListVentas()) {
            sb.append(String.format("%-10s %-20s $%-9.2f %s\n",
                    v.getIdVenta(),
                    v.getCliente().getNombre(),
                    v.getTotal(),
                    v.getFechaVenta().toString()
            ));
            // Si quieres productos especificos debajo:
            for (Producto p : v.getVenta()) {
                sb.append(String.format("    - %s | Cant: %d | $%.2f\n",
                        p.getNombre(), 1, p.getPrecioVenta())); // Asume 1 por producto, ajusta si tu modelo guarda cantidades
            }
        }
        if (num == 1) sb.append("No se realizaron ventas en esta fecha.\n");
        return sb.toString();
    }

    @Override
    public ArrayList<Venta> sacarArrayListVentas() {
        ArrayList<Venta> todas = super.sacarArrayListVentas();
        ArrayList<Venta> filtradas = new ArrayList<>();
        for (Venta v : todas) {
            if (v.getFechaVenta().isEqual(fecha)) filtradas.add(v);
        }
        return filtradas;
    }

    @Override
    public double sacarTotalVenta() {
        return sacarArrayListVentas().stream().mapToDouble(Venta::getTotal).sum();
    }

    @Override
    public double sacarTotalGanacia() {
        return sacarArrayListVentas().stream().mapToDouble(Venta::getTotal).sum();
    }
}
