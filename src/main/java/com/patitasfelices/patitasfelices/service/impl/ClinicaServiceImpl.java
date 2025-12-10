package com.patitasfelices.patitasfelices.service.impl;


import com.patitasfelices.patitasfelices.model.entity.HistorialClinico;
import com.patitasfelices.patitasfelices.repository.HistorialClinicoRepository;
import com.patitasfelices.patitasfelices.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    @Autowired
    private HistorialClinicoRepository historialRepository;

    @Override
    @Transactional
    public HistorialClinico registrarConsulta(HistorialClinico consulta) {
        if (consulta.getFechaConsulta() == null) {
            consulta.setFechaConsulta(LocalDateTime.now());
        }
        return historialRepository.save(consulta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinico> obtenerHistorialMascota(Long idMascota) {
        return historialRepository.findByMascota_IdMascotaOrderByFechaConsultaDesc(idMascota);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialClinico> buscarConsultaPorId(Long id) {
        return historialRepository.findById(id);
    }

    @Override
    @Transactional
    public HistorialClinico actualizarConsulta(HistorialClinico consulta) {
        return historialRepository.save(consulta);
    }
}
