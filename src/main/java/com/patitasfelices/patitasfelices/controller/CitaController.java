package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Cita;
import com.patitasfelices.patitasfelices.model.enums.EstadoCita;
import com.patitasfelices.patitasfelices.service.CitaService;
import com.patitasfelices.patitasfelices.service.MascotaService;
import com.patitasfelices.patitasfelices.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService; // Para listar veterinarios

    // Vista tipo Calendario (o lista simple por ahora)
    @GetMapping
    public String calendario(Model model) {
        model.addAttribute("citas", citaService.listarTodas());
        model.addAttribute("titulo", "Agenda de Citas");
        return "citas/lista_citas"; // Podría ser 'calendario.html' en el futuro
    }

    @GetMapping("/nueva")
    public String reservar(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaService.listarTodas());

        model.addAttribute("veterinarios", usuarioService.listarTodos());

        model.addAttribute("estados", EstadoCita.values());
        model.addAttribute("titulo", "Reservar Cita");
        return "citas/form_cita";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cita cita, RedirectAttributes flash) {
        try {
            citaService.guardar(cita);
            flash.addFlashAttribute("success", "Cita agendada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al agendar (posible conflicto de horario): " + e.getMessage());
            return "redirect:/citas/nueva";
        }
        return "redirect:/citas";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes flash) {
        citaService.cancelarCita(id);
        flash.addFlashAttribute("warning", "Cita cancelada");
        return "redirect:/citas";
    }
}
