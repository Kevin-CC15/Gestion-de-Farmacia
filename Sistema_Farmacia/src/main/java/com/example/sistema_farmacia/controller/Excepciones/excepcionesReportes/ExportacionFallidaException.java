package com.example.sistema_farmacia.controller.Excepciones.excepcionesReportes;

public class ExportacionFallidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public ExportacionFallidaException(String mensaje) {
        super(mensaje);
    }
}