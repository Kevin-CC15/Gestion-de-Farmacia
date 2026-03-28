package com.example.sistema_farmacia.controller.Excepciones.excepcionesProductos;

public class CategoriaInexistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoriaInexistenteException(String mensaje) {
        super(mensaje);
    }
}