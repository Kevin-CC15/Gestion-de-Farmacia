package com.example.sistema_farmacia.model.clasesplantillas;

import java.time.LocalDate;
import java.util.ArrayList;

public class Recibo {
    private String idRecibo;
    private LocalDate fecha;
    private Venta venta;
    private double total;
    private double descuento;
    private ArrayList<Producto> productos;

    // Constructor
    public Recibo(Venta venta) {
        this.venta = venta;
        this.idRecibo = generarIdRecibo();
        this.total = venta.getTotal();
        this.productos = venta.getVenta();
        this.fecha = venta.getFechaVenta();
        this.descuento = venta.aplicarDescuentoCliente();

    }

    public void listarProductos(){
        for(Producto p: productos){
            System.out.println("------------");
            System.out.println(p.getCodigo());
            System.out.println(p.getNombre());
            System.out.println(p.getPrecioVenta());
            System.out.println("------------");
        }
    }

    public String generarContenido() {
        // Lógica para formatear el contenido del recibo
        System.out.println("---------------------------");
        System.out.println("Fecha: " + fecha);
        System.out.println("Contenido del recibo: ID " + idRecibo);
        listarProductos();
        System.out.println("Total: " + total);
        System.out.println("Descuento: " + descuento);
        System.out.println("---------------------------");
        return "Contenido del recibo: ID " + idRecibo;
    }

    // Lógica para generar ID del recibo
    private String generarIdRecibo() {
        return "REC-" + System.currentTimeMillis();
    }

    //metodos get

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public Venta getVenta() {
        return venta;
    }
}