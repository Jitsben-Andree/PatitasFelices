package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.CategoriaProducto;
import com.patitasfelices.patitasfelices.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface InventarioService {

    // Gestión de Productos
    List<Producto> listarProductos();
    Producto guardarProducto(Producto producto);
    Optional<Producto> buscarProductoPorId(Long id);
    void eliminarProducto(Long id);

    // Búsquedas específicas
    List<Producto> buscarPorNombre(String nombre);
    List<Producto> buscarPorCategoria(Long idCategoria);

    // Lógica de Negocio: Alertas
    List<Producto> obtenerProductosConBajoStock(Integer stockMinimo);

    // Gestión de Categorías
    List<CategoriaProducto> listarCategorias();
}