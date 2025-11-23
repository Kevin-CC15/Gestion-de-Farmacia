package com.example.sistema_farmacia.model.clasesdata;

import com.example.sistema_farmacia.model.clasesplantillas.Venta;

import java.util.HashMap;
import java.util.Map;

public class VentasDB {
    // Atributo principal: Mapa que almacena recibos usando el ID como clave
    private Map<String, Venta> listaVentas;

    // Constructor VentasDB()
    public VentasDB() {
        this.listaVentas = new HashMap<>();
    }

    // Métodos Principales

    public void agregarVentas(Venta venta) {
        // Agrega el recibo a la lista usando su ID
        listaVentas.put(venta.getIdVenta(), venta);
    }

    public void eliminarVentas(String id) {
        // Elimina la venta de la lista buscándola por su ID
        listaVentas.remove(id);
    }

    public Map<String, Venta> getListaVentas() {
        // Retorna el Map<> completo
        return listaVentas;
    }
}