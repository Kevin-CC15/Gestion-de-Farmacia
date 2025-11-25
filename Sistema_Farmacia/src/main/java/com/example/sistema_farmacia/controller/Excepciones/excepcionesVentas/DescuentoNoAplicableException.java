package com.example.sistema_farmacia.controller.Excepciones.excepcionesVentas;

public class DescuentoNoAplicableException extends Exception {

    private static final long serialVersionUID = 1L;

    public DescuentoNoAplicableException(String mensaje) {
        super(mensaje);
    }
}