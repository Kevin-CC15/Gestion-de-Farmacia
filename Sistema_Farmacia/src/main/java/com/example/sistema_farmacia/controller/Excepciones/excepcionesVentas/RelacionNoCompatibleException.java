package com.example.sistema_farmacia.controller.Excepciones.excepcionesVentas;

public class RelacionNoCompatibleException extends Exception {

    private static final long serialVersionUID = 1L;

    public RelacionNoCompatibleException(String mensaje) {
        super(mensaje);
    }
}