package com.example.sistemafarmacia.Model;


public class Categoria {
    private String categoriaNombre;
    private String Descripcion;

    public Categoria(String categoriaNombre, String Descripcion) {
        this.categoriaNombre = categoriaNombre;
        this.Descripcion = Descripcion;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
