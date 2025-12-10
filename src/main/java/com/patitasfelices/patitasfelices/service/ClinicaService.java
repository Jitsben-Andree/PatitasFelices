package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.HistorialClinico;

import java.util.List;
import java.util.Optional;

public interface ClinicaService {

    HistorialClinico registrarConsulta(HistorialClinico consulta);
    List<HistorialClinico> obtenerHistorialMascota(Long idMascota);
    Optional<HistorialClinico> buscarConsultaPorId(Long id);

    HistorialClinico actualizarConsulta(HistorialClinico consulta);
}
