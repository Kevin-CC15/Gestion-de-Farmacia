package com.example.sistema_farmacia.controller.Excepciones.excepcionesPrincipales;

public class NoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoEncontradoException(String mensaje) {
        super(mensaje);
    }
}