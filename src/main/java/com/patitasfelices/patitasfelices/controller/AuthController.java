package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.model.entity.Usuario;
import com.patitasfelices.patitasfelices.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario de Login
    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    // Procesar los datos del Login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes flash) {

        Usuario usuario = usuarioService.validarCredenciales(username, password);

        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/home";
        } else {
            flash.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/login";
        }
    }

    // Cerrar Sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destruye la sesión
        return "redirect:/login";
    }
}
