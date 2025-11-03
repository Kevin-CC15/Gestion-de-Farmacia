package com.example.sistemafarmacia.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Recibo {
    private String idRecibo;
    private LocalDate fechaRecibo;
    private Ventas venta;
    private double total;
    private double descuentoAplicado;
    private ArrayList<Producto> productos;

    public Recibo() {

    }


    public Recibo(LocalDate localDate, Ventas ventas, double total, double descuentoAplicado) {

    }
}
