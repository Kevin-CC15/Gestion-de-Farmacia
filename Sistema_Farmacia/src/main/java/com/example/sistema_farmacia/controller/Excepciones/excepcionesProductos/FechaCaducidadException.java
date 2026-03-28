package com.example.sistema_farmacia.controller.Excepciones.excepcionesProductos;

public class FechaCaducidadException extends Exception {

    private static final long serialVersionUID = 1L;

    public FechaCaducidadException(String mensaje) {
        super(mensaje);
    }
}