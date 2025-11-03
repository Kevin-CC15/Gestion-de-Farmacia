package com.example.sistemafarmacia.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Ventas {

    private String idVenta;
    private LocalDate fechaVenta;
    private Cliente cliente;
    private String descripcion;
    private double descuentoAplicado;
    private double total;
    private boolean requiereReceta;
    private ArrayList<Producto> venta;

        public Ventas(Cliente cliente) {
        this.idVenta = generarIdVenta();
        this.fechaVenta = generarFecha();
        this.descuentoAplicado = 0;
        this.requiereReceta = true;
        this.cliente = cliente;
        this.descripcion = "";
        this.total = 0;
        this.venta = new ArrayList<>();
    }

    public String generarIdVenta() {
        return UUID.randomUUID().toString();
    }

    public LocalDate generarFecha() {
        return LocalDate.now();
    }

    public void agregarProducto(Producto producto) {
        this.venta.add(producto);
    }

    public double calcularTotal() {
       return this.total;
    }

    public Recibo generarRecibo(){
        return new Recibo();
    }

    public double aplicarDescuentoCliente(){
            return 0;
    }




    //Metodos Get
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

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public double getTotal() {
        return total;
    }

    public boolean getIsRequiereReceta() {
        return requiereReceta;
    }

    public ArrayList<Producto> getVenta() {
        return venta;
    }


    //Metodos Set
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
