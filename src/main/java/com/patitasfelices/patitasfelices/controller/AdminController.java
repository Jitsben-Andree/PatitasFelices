package com.patitasfelices.patitasfelices.controller;


import com.patitasfelices.patitasfelices.model.entity.Rol;
import com.patitasfelices.patitasfelices.model.entity.Usuario;
import com.patitasfelices.patitasfelices.repository.RolRepository;
import com.patitasfelices.patitasfelices.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    // Listar usuarios
    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Gestión de Usuarios");
        return "admin/lista_usuarios";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/nuevo")
    public String crear(Model model) {
        Usuario usuario = new Usuario();
        List<Rol> roles = rolRepository.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        model.addAttribute("titulo", "Nuevo Usuario");
        return "admin/form_usuario";
    }

    // Guardar usuario
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes flash) {

        try {
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success", "Usuario guardado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar usuario: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            usuarioService.eliminar(id);
            flash.addFlashAttribute("warning", "Usuario eliminado");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar el usuario (posiblemente tenga registros asociados)");
        }
        return "redirect:/admin/usuarios";
    }

    // Editar Usuario
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe");
            return "redirect:/admin/usuarios";
        }
        List<Rol> roles = rolRepository.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        model.addAttribute("titulo", "Editar Usuario");
        return "admin/form_usuario";
    }
}
