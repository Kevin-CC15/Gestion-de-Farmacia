package com.example.sistema_farmacia.controller.Excepciones.excepcionesGenerales;

public class AccesoConcurrenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public AccesoConcurrenteException(String mensaje) {
        super(mensaje);
    }
}