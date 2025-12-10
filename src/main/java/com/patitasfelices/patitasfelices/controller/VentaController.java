package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Venta;
import com.patitasfelices.patitasfelices.service.InventarioService;
import com.patitasfelices.patitasfelices.service.PropietarioService;
import com.patitasfelices.patitasfelices.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private InventarioService inventarioService;

    // Listado histórico de ventas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("titulo", "Historial de Ventas");
        return "ventas/lista_ventas";
    }

    // Pantalla de Punto de Venta (POS)
    @GetMapping("/pos")
    public String puntoVenta(Model model) {
        model.addAttribute("venta", new Venta());
        // Cargamos clientes y productos para que el cajero pueda seleccionarlos
        model.addAttribute("propietarios", propietarioService.listarTodos());
        model.addAttribute("productos", inventarioService.listarProductos());
        model.addAttribute("titulo", "Punto de Venta");
        return "ventas/pos";
    }

    @PostMapping("/procesar")
    public String procesarVenta(@ModelAttribute Venta venta, RedirectAttributes flash) {
        try {
            ventaService.registrarVenta(venta);
            flash.addFlashAttribute("success", "Venta registrada correctamente");
            return "redirect:/ventas";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al procesar venta: " + e.getMessage());
            return "redirect:/ventas/pos";
        }
    }

    // Ver detalle de una venta específica
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Venta venta = ventaService.buscarPorId(id).orElse(null);
        if (venta == null) {
            flash.addFlashAttribute("error", "Venta no encontrada");
            return "redirect:/ventas";
        }
        model.addAttribute("venta", venta);
        model.addAttribute("titulo", "Detalle de Venta #" + id);
        return "ventas/comprobante";
    }
}
