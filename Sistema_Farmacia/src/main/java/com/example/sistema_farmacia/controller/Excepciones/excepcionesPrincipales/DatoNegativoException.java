package com.example.sistema_farmacia.controller.Excepciones.excepcionesPrincipales;

public class DatoNegativoException extends Exception {

    private static final long serialVersionUID = 1L;

    public DatoNegativoException(String mensaje) {
        super(mensaje);
    }
}