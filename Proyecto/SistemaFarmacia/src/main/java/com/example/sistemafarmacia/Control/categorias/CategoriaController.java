package com.example.sistemafarmacia.Control.categorias;

import com.example.sistemafarmacia.Control.exeption.CategoriaException;
import com.example.sistemafarmacia.Control.exeption.CampoRequeridoException;
import com.example.sistemafarmacia.Model.Categoria;
import com.example.sistemafarmacia.Model.FarmaciaDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoriaController {
    private FarmaciaDB farmaciaDB;
    private CategoriaValidator validador;

    public CategoriaController(FarmaciaDB db) {
        this.farmaciaDB = db;
        this.validador = new CategoriaValidator();
    }

    public void agregarCategoria(String nombre, String descripcion) {
        try {
            validador.validarDatos(nombre, descripcion);
            validador.validarNombreUnico(nombre, farmaciaDB.getListaCategorias());
            validador.validarFormato(nombre, descripcion);

            Categoria nueva = new Categoria(nombre, descripcion);
            farmaciaDB.agregarCategoria(nueva);
            // mostrarExito("Categoría agregada correctamente");

        } catch (CategoriaException e) {
            manejarError(e);
        }
    }

    public void modificarCategoria(String nombreViejo, String nombreNuevo, String descripcion) {
        try {
            if (!farmaciaDB.getListaCategorias().containsKey(nombreViejo)) {
                throw new CampoRequeridoException("La categoría a modificar no existe.");
            }
            validador.validarDatos(nombreNuevo, descripcion);
            validador.validarFormato(nombreNuevo, descripcion);

            // Si cambió el nombre, verificar que el nuevo nombre no exista
            if (!nombreViejo.equals(nombreNuevo)) {
                validador.validarNombreUnico(nombreNuevo, farmaciaDB.getListaCategorias());
            }

            Categoria categoriaModificada = new Categoria(nombreNuevo, descripcion);
            farmaciaDB.modificarCategoria(nombreViejo, categoriaModificada);
            // mostrarExito("Categoría modificada correctamente");

        } catch (CategoriaException e) {
            manejarError(e);
        }
    }

    public void eliminarCategoria(String nombre) {
        try {
            if (!farmaciaDB.getListaCategorias().containsKey(nombre)) {
                throw new CampoRequeridoException("No existe la categoría que desea eliminar.");
            }
            farmaciaDB.eliminarCategoria(nombre);
            // mostrarExito("Categoría eliminada correctamente");

        } catch (CategoriaException e) {
            manejarError(e);
        }
    }

    public List<Categoria> buscarCategorias(String filtro) {
        List<Categoria> resultado = new ArrayList<>();
        Map<String, Categoria> categorias = farmaciaDB.getListaCategorias();
        for (Categoria categoria : categorias.values()) {
            if (categoria.getCategoriaNombre().toLowerCase().contains(filtro.toLowerCase())
                    || categoria.getDescripcion().toLowerCase().contains(filtro.toLowerCase())) {
                resultado.add(categoria);
            }
        }
        return resultado;
    }

    public void manejarError(CategoriaException e) {
        // Lógica para mostrar mensaje en la vista, loggear, etc.
        System.out.println("Error: " + e.getMessage());
    }
}
