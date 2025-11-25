package com.example.sistema_farmacia.controller.Excepciones.excepcionesGenerales;

public class OperacionFueraDeHorarioException extends Exception {

    private static final long serialVersionUID = 1L;

    public OperacionFueraDeHorarioException(String mensaje) {
        super(mensaje);
    }
}