package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    // Filtrar por categoría
    List<Producto> findByCategoria_IdCategoria(Long idCategoria);

    // ALERTA DE STOCK BAJO: Encuentra productos con stock menor a 'X' cantidad
    List<Producto> findByStockLessThan(Integer stockMinimo);
}
