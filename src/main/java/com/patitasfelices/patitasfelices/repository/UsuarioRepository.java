package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para el Login
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    // Validar si existe
    boolean existsByNombreUsuario(String nombreUsuario);

    // Buscar por rol
    Iterable<Usuario> findByRol_NombreRol(String nombreRol);
}