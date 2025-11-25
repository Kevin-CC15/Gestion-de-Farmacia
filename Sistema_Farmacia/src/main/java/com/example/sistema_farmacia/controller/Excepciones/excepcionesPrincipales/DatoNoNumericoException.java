package com.example.sistema_farmacia.controller.Excepciones.excepcionesPrincipales;

public class DatoNoNumericoException extends Exception {

    private static final long serialVersionUID = 1L;

    public DatoNoNumericoException(String mensaje) {
        super(mensaje);
    }
}