package com.example.sistema_farmacia.model.clasesdata;


import com.example.sistema_farmacia.model.clasesplantillas.Producto;
import java.util.HashMap;
import java.util.Map;

public class ProductosDB {
    // Atributo principal: Mapa que almacena productos usando el código como clave
    private Map<String, Producto> listaProductos;

    // Constructor ProductosDB()
    public ProductosDB() {
        this.listaProductos = new HashMap<>();
    }

    // Métodos Principales

    public void agregarProducto(Producto producto) {
        //agrega el producto al Map
        listaProductos.put(producto.getCodigo(), producto);
    }

    public void eliminarProducto(String codigo) {
        // Elimina el producto de la lista por su código
        listaProductos.remove(codigo);
    }

    public void modificarProducto(String codigoViejo, Producto producto) {
        // Elimina el producto anterior y agrega el objeto Producto modificado
        listaProductos.remove(codigoViejo);
        listaProductos.put(producto.getCodigo(), producto);
    }

    public Map<String, Producto> getListaProductos() {
        // Retorna el Map<> completo
        return listaProductos;
    }
}