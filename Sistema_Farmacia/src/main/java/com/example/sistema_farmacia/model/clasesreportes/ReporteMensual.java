package com.example.sistema_farmacia.model.clasesreportes;

import com.example.sistema_farmacia.model.clasesdata.VentasDB;
import com.example.sistema_farmacia.model.clasesplantillas.Venta;

import java.util.ArrayList;

public class ReporteMensual extends ReporteVentas {
    private final int anio;
    private final int mes;

    public ReporteMensual(VentasDB ventasDB, int anio, int mes) {
        super(ventasDB);
        this.anio = anio;
        this.mes = mes;
    }

    @Override
    public ArrayList<Venta> sacarArrayListVentas() {
        ArrayList<Venta> todas = super.sacarArrayListVentas();
        ArrayList<Venta> filtradas = new ArrayList<>();
        for (Venta v : todas) {
            if (v.getFechaVenta().getYear() == anio && v.getFechaVenta().getMonthValue() == mes)
                filtradas.add(v);
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
