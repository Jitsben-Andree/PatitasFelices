package com.patitasfelices.patitasfelices.controller;

import com.patitasfelices.patitasfelices.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/recepcion")
public class RecepcionController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String dashboard(Model model) {

        LocalDateTime inicioDia = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime finDia = LocalDateTime.now().with(LocalTime.MAX);

        model.addAttribute("citasHoy", citaService.buscarPorRangoFechas(inicioDia, finDia));


        model.addAttribute("titulo", "Panel de Recepción");
        return "home/index_recepcion";
    }
}
