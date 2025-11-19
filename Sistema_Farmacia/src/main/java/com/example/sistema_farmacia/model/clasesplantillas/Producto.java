package model.clasesplantillas;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsula la información relevante de un artículo gestionado por la farmacia.
 */
public class Producto {
    // Atributos privados según el UML
    private String nombre;
    private String descripcion;
    private String codigo;
    private double precioVenta;
    private LocalDate fechaCaducidad;
    private int unidadesExi;
    private ArrayList<model.clasesplantillas.Categoria> categoria; // Relación

    // Constructor Producto(nom: String, desc: String, cod: String, precio: double, fecha: LocalDate, unidades: int)
    public Producto(String nom, String desc, String cod, double precio, LocalDate fecha, int unidades) {
        this.nombre = nom;
        this.descripcion = desc;
        this.codigo = cod;
        this.precioVenta = precio;
        this.fechaCaducidad = fecha;
        this.unidadesExi = unidades;
        this.categoria = new ArrayList<>();
    }

    // Métodos de lógica de negocio

    public void agregarCategoria(model.clasesplantillas.Categoria categoria) {
        this.categoria.add(categoria);
    }

    public void eliminarCategoria(model.clasesplantillas.Categoria categoria) {
        this.categoria.remove(categoria);
    }

    // Métodos Get (Accesores)

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public int getUnidadesExi() {
        return unidadesExi;
    }

    public ArrayList<model.clasesplantillas.Categoria> getCategoria() {
        return categoria;
    }

    // Métodos Set (Mutadores)

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setUnidadesExi(int unidadesExi) {
        this.unidadesExi = unidadesExi;
    }
}