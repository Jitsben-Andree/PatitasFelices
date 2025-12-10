package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Cita;
import com.patitasfelices.patitasfelices.model.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Citas de un veterinario específico
    List<Cita> findByVeterinario_IdUsuario(Long idVeterinario);

    // Citas de una mascota (Historial de citas)
    List<Cita> findByMascota_IdMascota(Long idMascota);

    // Citas por estado (ej: ver todas las PENDIENTES)
    List<Cita> findByEstado(EstadoCita estado);

    // Citas en un rango de fechas (Ideal para la vista de Calendario Semanal/Mensual)
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}