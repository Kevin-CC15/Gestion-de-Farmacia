package com.example.sistemafarmacia.Model;


import java.util.HashMap;
import java.util.Map;

public class FarmaciaDB {
    private Map<String,Cliente> listaClientes;
    private Map<String,Recibo> listaVentas;
    private Map<String,Categoria> listaCategorias;
    private Map<String,Producto> listaProductos;
    private Map<String,User> listaUsuariosPrograma;

    FarmaciaDB(){
        listaClientes = new HashMap<String,Cliente>();
        listaVentas = new HashMap<String,Recibo>();
        listaCategorias = new HashMap<String,Categoria>();
        listaProductos = new HashMap<String,Producto>();
        listaUsuariosPrograma = new HashMap<String,User>();
    }

    public void agregarCliente(Cliente cliente){
        listaClientes.put(cliente.getIdCliente(),cliente);
    }

    public void eliminarCliente(Cliente cliente){
        listaClientes.remove(cliente.getIdCliente());
    }

    public void agregarVentas(Recibo recibo){
    }

    public void eliminarVentas(Recibo recibo){

    }

    public void agregarCategoria(Categoria categoria){
        listaCategorias.put(categoria.getCategoriaNombre(), categoria);
    }

    public void eliminarCategoria(String categoria){
        listaCategorias.remove(categoria);
    }

    public void modificarCategoria(String nombreViejo, Categoria categoriaModificada) {
        listaCategorias.remove(nombreViejo);
        listaCategorias.put(categoriaModificada.getCategoriaNombre(), categoriaModificada);
    }

    public void agregarProducto(Producto producto){

    }

    public void eliminarProducto(Producto producto){

    }

    public Map<String, Cliente> getListaClientes() {
        return listaClientes;
    }

    public Map<String, Recibo> getListaVentas() {
        return listaVentas;
    }

    public Map<String, Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public Map<String, Producto> getListaProductos() {
        return listaProductos;
    }

    public Map<String, User> getListaUsuariosPrograma() {
        return listaUsuariosPrograma;
    }


}
