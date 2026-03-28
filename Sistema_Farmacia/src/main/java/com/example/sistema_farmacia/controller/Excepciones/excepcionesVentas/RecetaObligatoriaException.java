package com.example.sistema_farmacia.controller.Excepciones.excepcionesVentas;

public class RecetaObligatoriaException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecetaObligatoriaException(String mensaje) {
        super(mensaje);
    }
}