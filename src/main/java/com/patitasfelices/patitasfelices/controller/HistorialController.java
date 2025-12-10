package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.HistorialClinico;
import com.patitasfelices.patitasfelices.model.entity.Mascota;
import com.patitasfelices.patitasfelices.model.entity.Usuario;
import com.patitasfelices.patitasfelices.service.ClinicaService;
import com.patitasfelices.patitasfelices.service.MascotaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/ver/{idMascota}")
    public String verExpediente(@PathVariable Long idMascota, Model model) {
        Mascota mascota = mascotaService.buscarPorId(idMascota).orElse(null);
        if (mascota == null) {
            return "redirect:/mascotas";
        }

        model.addAttribute("mascota", mascota);
        model.addAttribute("historial", clinicaService.obtenerHistorialMascota(idMascota));
        model.addAttribute("titulo", "Expediente Médico: " + mascota.getNombre());
        return "historial/expediente";
    }

    @GetMapping("/nuevo/{idMascota}")
    public String nuevaConsulta(@PathVariable Long idMascota, Model model, RedirectAttributes flash) {
        Mascota mascota = mascotaService.buscarPorId(idMascota).orElse(null);
        if (mascota == null) {
            flash.addFlashAttribute("error", "Mascota no encontrada");
            return "redirect:/mascotas";
        }

        HistorialClinico consulta = new HistorialClinico();
        consulta.setMascota(mascota);

        model.addAttribute("consulta", consulta);
        model.addAttribute("titulo", "Nueva Consulta");
        return "historial/form_consulta";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HistorialClinico consulta,
                          HttpSession session,
                          RedirectAttributes flash) {

        Usuario veterinario = (Usuario) session.getAttribute("usuarioLogueado");
        if (veterinario != null) {
            consulta.setVeterinario(veterinario);
        }

        clinicaService.registrarConsulta(consulta);
        flash.addFlashAttribute("success", "Consulta registrada correctamente");

        // Redirigir de vuelta al expediente de la mascota
        return "redirect:/historial/ver/" + consulta.getMascota().getIdMascota();
    }
}
