package com.example.sistema_farmacia.controller.Excepciones.excepcionesPrincipales;

public class DuplicadoException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicadoException(String mensaje) {
        super(mensaje);
    }
}