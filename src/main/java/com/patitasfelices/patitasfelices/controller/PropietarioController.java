package com.patitasfelices.patitasfelices.controller;


import com.patitasfelices.patitasfelices.model.entity.Propietario;
import com.patitasfelices.patitasfelices.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("propietarios", propietarioService.listarTodos());
        model.addAttribute("titulo", "Listado de Clientes");
        return "propietarios/lista_propietarios";
    }

    @GetMapping("/nuevo")
    public String crear(Model model) {
        model.addAttribute("propietario", new Propietario());
        model.addAttribute("titulo", "Registrar Cliente");
        return "propietarios/form_propietario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Propietario propietario, RedirectAttributes flash) {
        try {
            propietarioService.guardar(propietario);
            flash.addFlashAttribute("success", "Cliente registrado con éxito");
        } catch (DataIntegrityViolationException e) {
            // Capturamos específicamente el error de duplicidad de datos (correo repetido)
            flash.addFlashAttribute("error", "El correo electrónico ya está registrado en el sistema. Intente con otro.");
            return "redirect:/propietarios/nuevo";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
            return "redirect:/propietarios/nuevo";
        }
        return "redirect:/propietarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Propietario prop = propietarioService.buscarPorId(id).orElse(null);
        if (prop == null) {
            flash.addFlashAttribute("error", "El cliente no existe");
            return "redirect:/propietarios";
        }
        model.addAttribute("propietario", prop);
        model.addAttribute("titulo", "Editar Cliente");
        return "propietarios/form_propietario";
    }
}