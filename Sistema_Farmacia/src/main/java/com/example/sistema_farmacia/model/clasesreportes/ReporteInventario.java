package com.example.sistema_farmacia.model.clasesreportes;

import com.example.sistema_farmacia.model.clasesdata.ProductosDB;
import com.example.sistema_farmacia.model.clasesplantillas.Producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReporteInventario {
    private final ProductosDB productoDB;
    private final LocalDate fechaGeneracion;
    private final ArrayList<Producto> productosExistentes;

    // Constructor
    public ReporteInventario(ProductosDB productoDB) {
        this.productoDB = productoDB;
        this.fechaGeneracion = LocalDate.now();
        this.productosExistentes = convertirArraylist();
    }

    // Todos los productos
    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(productosExistentes);
    }

    // Productos con 0 stock
    public ArrayList<Producto> listarProductosAgotar() {
        return productosExistentes.stream()
                .filter(p -> p.getUnidadesExi() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Productos que caducan en los próximos 30 días
    public ArrayList<Producto> listarProductosCaducar() {
        LocalDate hoy = LocalDate.now();
        return productosExistentes.stream()
                .filter(p -> p.getFechaCaducidad() != null &&
                        !p.getFechaCaducidad().isBefore(hoy) &&
                        !p.getFechaCaducidad().isAfter(hoy.plusDays(30)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Productos sin categoría
    public ArrayList<Producto> listarProductosSinCategoria() {
        return productosExistentes.stream()
                .filter(p -> p.getCategoria() == null || p.getCategoria().isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Método auxiliar para convertir el Map del DB en ArrayList
    public ArrayList<Producto> convertirArraylist() {
        return new ArrayList<>(productoDB.getListaProductos().values());
    }

    // Texto resumen automático (puedes usarlo como .generarReporte())
    public String crearReporteInventario() {
        return "Reporte de Inventario generado al " + fechaGeneracion.toString()
                + "\nProductos totales: " + productosExistentes.size();
    }
}
