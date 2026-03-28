package com.example.sistema_farmacia.controller.Excepciones.excepcionesRecibo;

public class CampoVacioException extends Exception {

    private static final long serialVersionUID = 1L;

    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}