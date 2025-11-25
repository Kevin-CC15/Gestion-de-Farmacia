package com.example.sistema_farmacia.controller.Excepciones.excepcionesReportes;

public class ReporteSinDatosException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReporteSinDatosException(String mensaje) {
        super(mensaje);
    }
}