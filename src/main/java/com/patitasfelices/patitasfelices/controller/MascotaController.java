package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Mascota;
import com.patitasfelices.patitasfelices.repository.EspecieRepository;
import com.patitasfelices.patitasfelices.repository.RazaRepository;
import com.patitasfelices.patitasfelices.service.MascotaService;
import com.patitasfelices.patitasfelices.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RazaRepository razaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mascotas", mascotaService.listarTodas());
        model.addAttribute("titulo", "Pacientes (Mascotas)");
        return "mascotas/lista_mascotas";
    }

    @GetMapping("/nuevo")
    public String crear(Model model) {
        model.addAttribute("mascota", new Mascota());
        cargarSelectores(model);
        model.addAttribute("titulo", "Registrar Mascota");
        return "mascotas/form_mascota";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mascota mascota, RedirectAttributes flash) {
        mascotaService.guardar(mascota);
        flash.addFlashAttribute("success", "Mascota guardada con éxito");
        return "redirect:/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Mascota mascota = mascotaService.buscarPorId(id).orElse(null);
        if (mascota == null) {
            flash.addFlashAttribute("error", "La mascota no existe");
            return "redirect:/mascotas";
        }
        model.addAttribute("mascota", mascota);
        cargarSelectores(model);
        model.addAttribute("titulo", "Editar Mascota");
        return "mascotas/form_mascota";
    }

    private void cargarSelectores(Model model) {
        model.addAttribute("propietarios", propietarioService.listarTodos());
        model.addAttribute("especies", especieRepository.findAll());
        model.addAttribute("razas", razaRepository.findAll());
    }
}
