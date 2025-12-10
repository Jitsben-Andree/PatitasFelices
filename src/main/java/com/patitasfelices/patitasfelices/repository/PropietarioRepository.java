package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Long> {

    // Buscar propietario por correo
    Propietario findByPersona_CorreoElectronico(String correo);

    // Búsqueda flexible por nombre del propietario
    List<Propietario> findByPersona_NombresContainingIgnoreCase(String nombre);
}