package model.clasesplantillas;

import java.util.ArrayList;

/**
 * Representa un grupo o tipo de productos del sistema.
 */
public class Categoria {
    // Atributos privados según el UML
    private String categoriaNombre;
    private String descripcion;
    private ArrayList<model.clasesplantillas.Producto> productos; // Relación

    // Constructor Categoria(categoriaNombre: String, descripcion: String)
    public Categoria(String categoriaNombre, String descripcion) {
        this.categoriaNombre = categoriaNombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    // Métodos de lógica de negocio

    public void agregarProducto(model.clasesplantillas.Producto producto) {
        this.productos.add(producto);
    }

    public void eliminarProducto(model.clasesplantillas.Producto producto) {
        this.productos.remove(producto);
    }

    // Métodos Get (Accesores)

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<model.clasesplantillas.Producto> getProductos() {
        return productos;
    }

    // Métodos Set (Mutadores)

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}