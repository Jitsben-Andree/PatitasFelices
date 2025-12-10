package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    void eliminar(Long id);

    Usuario validarCredenciales(String username, String password);
}