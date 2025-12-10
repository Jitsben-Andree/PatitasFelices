package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Listar todas las mascotas de un dueño específico
    List<Mascota> findByPropietario_IdPropietario(Long idPropietario);

    // Buscar mascotas por nombre
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
}