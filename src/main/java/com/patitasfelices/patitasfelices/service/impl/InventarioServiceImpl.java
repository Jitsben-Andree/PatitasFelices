package com.patitasfelices.patitasfelices.service.impl;

import com.patitasfelices.patitasfelices.model.entity.CategoriaProducto;
import com.patitasfelices.patitasfelices.model.entity.Producto;
import com.patitasfelices.patitasfelices.repository.CategoriaProductoRepository;
import com.patitasfelices.patitasfelices.repository.ProductoRepository;
import com.patitasfelices.patitasfelices.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerProductosConBajoStock(Integer stockMinimo) {
        return productoRepository.findByStockLessThan(stockMinimo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProducto> listarCategorias() {
        return categoriaRepository.findAll();
    }
}