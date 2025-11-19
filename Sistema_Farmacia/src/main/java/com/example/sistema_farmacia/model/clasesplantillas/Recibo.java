package model.clasesplantillas;

import java.time.LocalDate;
import java.util.ArrayList;


public class Recibo {
    private String idRecibo;
    private LocalDate fecha;
    private Venta venta; // Relación directa con la Venta que lo origina
    private double total;
    private double descuento;
    private double descuentoAplicado;
    private ArrayList<Producto> productos;

    // Constructor Recibo(venta : Venta)
    public Recibo(Venta venta) {
        this.venta = venta;
        // Inicializa atributos usando datos de la Venta
        this.idRecibo = generarIdRecibo();
        this.fecha = LocalDate.now(); // O usar venta.getFechaVenta() si Venta lo tiene
        this.total = venta.getTotal();
        this.descuento = 0.0; // Se calcularía en un sistema real
        this.descuentoAplicado = venta.aplicarDescuentoCliente(); // Reutiliza lógica de Venta
        this.productos = venta.getVenta(); // Lista de productos de la venta
    }

    // Métodos Principales (Lógica de Negocio)

    public void listarProductos() {
        System.out.println("Listado de productos para el recibo " + idRecibo);
    }

    public String generarContenido() {
        return "Contenido detallado del recibo ID: " + idRecibo;
    }

    public String generarIdRecibo() {
        return "REC-" + System.currentTimeMillis();
    }

    public boolean validar() {
        return true;
    }

    // Métodos Get (Accesores)

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