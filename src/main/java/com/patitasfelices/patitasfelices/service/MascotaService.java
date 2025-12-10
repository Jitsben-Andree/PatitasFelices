package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {

    List<Mascota> listarTodas();
    Mascota guardar(Mascota mascota);
    Optional<Mascota> buscarPorId(Long id);
    void eliminar(Long id);


    List<Mascota> buscarPorPropietario(Long idPropietario);
    List<Mascota> buscarPorNombre(String nombre);
}