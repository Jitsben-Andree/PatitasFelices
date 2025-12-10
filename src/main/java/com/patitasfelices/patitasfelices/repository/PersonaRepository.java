package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCorreoElectronico(String correoElectronico);

    // Búsqueda por coincidencia de nombre o apellido

    Iterable<Persona> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombres, String apellidos);
}
