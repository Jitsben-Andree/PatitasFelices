package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.Cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<Cita> listarTodas();
    Cita guardar(Cita cita) throws Exception; // Puede lanzar excepción si hay conflicto
    Optional<Cita> buscarPorId(Long id);
    void cancelarCita(Long id);

    List<Cita> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
}
