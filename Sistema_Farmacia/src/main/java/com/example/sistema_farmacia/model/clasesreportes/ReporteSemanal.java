package com.example.sistema_farmacia.model.clasesreportes;

import com.example.sistema_farmacia.model.clasesdata.VentasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Venta;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReporteSemanal extends ReporteVentas {
    private final LocalDate fechaInicio;

    public ReporteSemanal(VentasDB ventasDB, LocalDate fechaInicio) {
        super(ventasDB);
        this.fechaInicio = fechaInicio;
    }


    @Override
    public ArrayList<Venta> sacarArrayListVentas() {
        ArrayList<Venta> todas = super.sacarArrayListVentas();
        ArrayList<Venta> filtradas = new ArrayList<>();
        LocalDate fin = fechaInicio.plusDays(6);
        for (Venta v : todas) {
            if (!v.getFechaVenta().isBefore(fechaInicio) && !v.getFechaVenta().isAfter(fin)) filtradas.add(v);
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
