package com.example.sistema_farmacia.controller.Excepciones.excepcionesRecibo;

public class RelacionNoCompatibleException extends Exception {

    private static final long serialVersionUID = 1L;

    public RelacionNoCompatibleException(String mensaje) {
        super(mensaje);
    }
}