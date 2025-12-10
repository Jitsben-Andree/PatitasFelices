package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Especie;
import com.patitasfelices.patitasfelices.model.entity.Raza;
import com.patitasfelices.patitasfelices.repository.EspecieRepository;
import com.patitasfelices.patitasfelices.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/catalogos")
public class CatalogoController {

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RazaRepository razaRepository;

    // Vista principal de catálogos
    @GetMapping
    public String index(Model model) {
        // Cargamos las listas para mostrarlas en las tablas
        model.addAttribute("especies", especieRepository.findAll());
        model.addAttribute("razas", razaRepository.findAll());

        // Objetos vacíos para los formularios de agregar
        model.addAttribute("nuevaEspecie", new Especie());
        model.addAttribute("nuevaRaza", new Raza());

        model.addAttribute("titulo", "Gestión de Catálogos");
        return "admin/catalogos";
    }

    // Guardar Especie
    @PostMapping("/especie/guardar")
    public String guardarEspecie(@ModelAttribute Especie especie, RedirectAttributes flash) {
        try {
            especieRepository.save(especie);
            flash.addFlashAttribute("success", "Especie registrada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar especie: " + e.getMessage());
        }
        return "redirect:/admin/catalogos";
    }

    // Guardar Raza
    @PostMapping("/raza/guardar")
    public String guardarRaza(@ModelAttribute Raza raza, RedirectAttributes flash) {
        try {
            razaRepository.save(raza);
            flash.addFlashAttribute("success", "Raza registrada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar raza: " + e.getMessage());
        }
        return "redirect:/admin/catalogos";
    }

    // Eliminar Especie
    @GetMapping("/especie/eliminar/{id}")
    public String eliminarEspecie(@PathVariable Long id, RedirectAttributes flash) {
        try {
            especieRepository.deleteById(id);
            flash.addFlashAttribute("warning", "Especie eliminada");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar (probablemente tenga razas o mascotas asociadas)");
        }
        return "redirect:/admin/catalogos";
    }

    // Eliminar Raza
    @GetMapping("/raza/eliminar/{id}")
    public String eliminarRaza(@PathVariable Long id, RedirectAttributes flash) {
        try {
            razaRepository.deleteById(id);
            flash.addFlashAttribute("warning", "Raza eliminada");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar (tiene mascotas asociadas)");
        }
        return "redirect:/admin/catalogos";
    }
}
