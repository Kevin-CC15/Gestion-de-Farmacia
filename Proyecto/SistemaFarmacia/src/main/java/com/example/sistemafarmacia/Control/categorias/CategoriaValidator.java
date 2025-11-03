package com.example.sistemafarmacia.Control.categorias;
import com.example.sistemafarmacia.Control.exeption.CampoRequeridoException;
import com.example.sistemafarmacia.Control.exeption.DatoDuplicadoException;
import com.example.sistemafarmacia.Control.exeption.FormatoInvalidoException;
import com.example.sistemafarmacia.Model.Categoria;

import java.util.Map;
public class CategoriaValidator {
    public void validarDatos(String nombre, String descripcion) throws CampoRequeridoException {
        if (nombre == null || nombre.trim().isEmpty())
            throw new CampoRequeridoException("El nombre es obligatorio");
        if (descripcion == null || descripcion.trim().isEmpty())
            throw new CampoRequeridoException("La descripción es obligatoria");
    }

    public void validarNombreUnico(String nombre, Map<String, Categoria> listaActual) throws DatoDuplicadoException {
        if (listaActual.containsKey(nombre))
            throw new DatoDuplicadoException("Ya existe una categoría con ese nombre");
    }

    public void validarFormato(String nombre, String descripcion) throws FormatoInvalidoException {
        if (nombre.length() > 40)
            throw new FormatoInvalidoException("El nombre no debe exceder 40 caracteres");
        if (!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]+"))
            throw new FormatoInvalidoException("El nombre solo puede contener letras, números y espacios");
        if (descripcion.length() > 200)
            throw new FormatoInvalidoException("La descripción no debe exceder 200 caracteres");
    }
}
