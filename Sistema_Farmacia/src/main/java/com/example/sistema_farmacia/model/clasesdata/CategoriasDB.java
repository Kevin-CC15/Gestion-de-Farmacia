package com.example.sistema_farmacia.model.clasesdata;

import com.example.sistema_farmacia.model.clasesplantillas.Categoria;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriasDB {
    private static final String CATEGORIA_DEFAULT_NOMBRE = "sin categoria";
    private static final String CATEGORIA_DEFAULT_DESC = "producto sin categoría";

    private Map<String, Categoria> listaCategorias;

    public CategoriasDB() {
        this.listaCategorias = new HashMap<>();
        // Garantiza que la categoría especial existe siempre
        if (!listaCategorias.containsKey(CATEGORIA_DEFAULT_NOMBRE)) {
            listaCategorias.put(CATEGORIA_DEFAULT_NOMBRE,
                    new Categoria(CATEGORIA_DEFAULT_NOMBRE, CATEGORIA_DEFAULT_DESC));
        }
    }

    public void agregarCategoria(Categoria categoria) {
        if (categoria.getCategoriaNombre().equalsIgnoreCase(CATEGORIA_DEFAULT_NOMBRE)) return;
        listaCategorias.put(categoria.getCategoriaNombre(), categoria);
    }

    public void eliminarCategoria(String nombre) {
        if (nombre.equalsIgnoreCase(CATEGORIA_DEFAULT_NOMBRE)) return;  // No se elimina la categoría especial
        listaCategorias.remove(nombre);
    }

    public void modificarCategoria(String nombreViejo, Categoria categoria) {
        if (nombreViejo.equalsIgnoreCase(CATEGORIA_DEFAULT_NOMBRE)) return;
        listaCategorias.remove(nombreViejo);
        if (!categoria.getCategoriaNombre().equalsIgnoreCase(CATEGORIA_DEFAULT_NOMBRE)) {
            listaCategorias.put(categoria.getCategoriaNombre(), categoria);
        }
    }

    /** Retorna todas las categorías (incluye la especial) */
    public Map<String, Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /** Para ComboBox/ListView de productos:
     * Retorna solo las categorías que se pueden elegir (NO la categoría por defecto) */
    public List<Categoria> getCategoriasParaProductos() {
        return listaCategorias.values().stream()
                .filter(cat -> !cat.getCategoriaNombre().equalsIgnoreCase(CATEGORIA_DEFAULT_NOMBRE))
                .collect(Collectors.toList());
    }

    /** Devuelve la categoría especial, útil si hacen falta o no existe ninguna categoría real */
    public Categoria getCategoriaDefault() {
        return listaCategorias.get(CATEGORIA_DEFAULT_NOMBRE);
    }
}
