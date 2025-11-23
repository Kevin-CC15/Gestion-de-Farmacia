package com.example.sistema_farmacia.model.clasesplantillas;

public class Categoria {
    private String categoriaNombre;
    private String descripcion;

    // Constructor
    public Categoria(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Categoria(String categoriaNombre, String descripcion) {
        this.categoriaNombre = categoriaNombre;
        this.descripcion = descripcion;
    }

    //metodos get y set

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}