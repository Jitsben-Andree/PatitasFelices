package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Producto;
import com.patitasfelices.patitasfelices.repository.CategoriaProductoRepository;
import com.patitasfelices.patitasfelices.repository.ProveedorRepository;
import com.patitasfelices.patitasfelices.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private CategoriaProductoRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", inventarioService.listarProductos());
        model.addAttribute("titulo", "Gestión de Inventario");
        return "inventario/lista_productos";
    }

    @GetMapping("/nuevo")
    public String crear(Model model) {
        model.addAttribute("producto", new Producto());
        cargarSelectores(model);
        model.addAttribute("titulo", "Nuevo Producto");
        return "inventario/form_producto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes flash) {
        inventarioService.guardarProducto(producto);
        flash.addFlashAttribute("success", "Producto guardado con éxito");
        return "redirect:/inventario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Producto producto = inventarioService.buscarProductoPorId(id).orElse(null);
        if (producto == null) {
            flash.addFlashAttribute("error", "El producto no existe");
            return "redirect:/inventario";
        }
        model.addAttribute("producto", producto);
        cargarSelectores(model);
        model.addAttribute("titulo", "Editar Producto");
        return "inventario/form_producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            inventarioService.eliminarProducto(id);
            flash.addFlashAttribute("warning", "Producto eliminado");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar (puede tener ventas asociadas)");
        }
        return "redirect:/inventario";
    }

    private void cargarSelectores(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("proveedores", proveedorRepository.findAll());
    }
}
