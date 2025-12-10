package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.Propietario;

import java.util.List;
import java.util.Optional;

public interface PropietarioService {
    List<Propietario> listarTodos();
    Propietario guardar(Propietario propietario);
    Optional<Propietario> buscarPorId(Long id);
    void eliminar(Long id);
    List<Propietario> buscarPorNombre(String criterio);
}

