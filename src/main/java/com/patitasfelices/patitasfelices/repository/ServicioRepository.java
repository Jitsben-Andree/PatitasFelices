package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    // Buscar servicio por nombre exacto
    boolean existsByNombreServicio(String nombreServicio);
}