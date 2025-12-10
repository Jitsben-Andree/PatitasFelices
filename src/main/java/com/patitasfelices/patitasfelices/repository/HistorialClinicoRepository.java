package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {

    // Obtenertodo el historial
    List<HistorialClinico> findByMascota_IdMascotaOrderByFechaConsultaDesc(Long idMascota);

    // Consultas realizadas por un veterinario específico
    List<HistorialClinico> findByVeterinario_IdUsuario(Long idVeterinario);
}