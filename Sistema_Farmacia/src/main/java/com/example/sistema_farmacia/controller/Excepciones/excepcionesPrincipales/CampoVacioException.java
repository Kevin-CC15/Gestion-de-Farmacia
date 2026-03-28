package com.example.sistema_farmacia.controller.Excepciones.excepcionesPrincipales;

/**
 * Excepción para operaciones de agregar, modificar y crear cuando falta
 * información obligatoria en cualquier clase (Producto, Cliente, Venta, etc.).
 */
public class CampoVacioException extends Exception {

    private static final long serialVersionUID = 1L;

    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}