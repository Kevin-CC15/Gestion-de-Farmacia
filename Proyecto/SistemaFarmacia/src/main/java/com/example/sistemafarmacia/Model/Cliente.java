package com.example.sistemafarmacia.Model;

import java.util.ArrayList;

public class Cliente {

    private String idCliente;
    private String nombre;
    private String direccion;
    private String numeroContacto;
    private boolean esClienteAmigo;
    private double porcentajeDescuento;
    private ArrayList<Ventas> historialCompras;

    public Cliente(String nombre, String direccion, String numeroContacto, Boolean esClienteAmigo) {
        this.idCliente = generarID();
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroContacto = numeroContacto;
        this.porcentajeDescuento = 0;
        this.historialCompras = new ArrayList<>();
        this.esClienteAmigo = esClienteAmigo;
    }

    public String generarID() {
        return "s";
    }

    public void agregarCompra(Ventas ventas) {
        this.historialCompras.add(ventas);
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public boolean isEsClienteAmigo() {
        return esClienteAmigo;
    }

    public void setEsClienteAmigo(boolean esClienteAmigo) {
        this.esClienteAmigo = esClienteAmigo;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public ArrayList<Ventas> getHistorialCompras() {
        return historialCompras;
    }

}
