package com.example.sistema_farmacia.controller.Excepciones.excepcionesProductos;

public class ProductoAgotadoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductoAgotadoException(String mensaje) {
        super(mensaje);
    }
}