package com.example.sistema_farmacia.model.clasesplantillas;

import java.time.LocalDate;
import java.util.ArrayList;


public class Venta {
    private String idVenta;
    private LocalDate fechaVenta;
    private Cliente cliente;
    private String descripcion;
    private double total;
    private boolean requiereReceta;
    private Recibo recibo;
    private ArrayList<Producto> venta; // Lista de productos vendidos

    // Constructor Ventas(cliente : Cliente)
    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.idVenta = generarIdVenta();
        this.fechaVenta = generarFecha();
        this.venta = new ArrayList<>();
        this.total = calcularTotal();
        this.requiereReceta = false; // Valor inicial
    }

    // Métodos Principales (Lógica de Negocio)

    public String generarIdVenta() {
        // Lógica para generar un ID único
        return "VEN-" + System.currentTimeMillis();
    }

    public LocalDate generarFecha() {
        // Genera la fecha actual
        return LocalDate.now();
    }

    public void agregarProducto(Producto producto) {
        this.venta.add(producto);
    }

    public double calcularTotal() {
        double subtotal = 0.0;
        for (Producto p : venta) {
            subtotal += p.getPrecioVenta();
        }

        // actualizamos el total con la suma de los productos
        this.total = subtotal;

        //calculamos el descuento (que usará el valor que acabamos de asignar)
        double descuento = aplicarDescuentoCliente();

        this.total = subtotal - descuento;

        return this.total;
    }

    public double aplicarDescuentoCliente() {
        return this.total * (cliente.getPorcentajeDescuento() / 100.0);
    }

    public void generarRecibo() {
        this.recibo = new Recibo(this);
    }

    // Métodos Get (Accesores)

    public String getIdVenta() {
        return idVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTotal() {
        return total;
    }

    public boolean getRequiereRecesa() {
        return requiereReceta;
    }

    public ArrayList<Producto> getVenta() {
        return venta;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    // Métodos Set (Mutadores)

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }
}