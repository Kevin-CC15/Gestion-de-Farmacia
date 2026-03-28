package com.example.sistema_farmacia.controller.Excepciones.excepcionesReportes;

public class FechaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}