package com.patitasfelices.patitasfelices.service.impl;

import com.patitasfelices.patitasfelices.model.entity.Cita;
import com.patitasfelices.patitasfelices.model.enums.EstadoCita;
import com.patitasfelices.patitasfelices.repository.CitaRepository;
import com.patitasfelices.patitasfelices.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    @Override
    @Transactional
    public Cita guardar(Cita cita) throws Exception {

        if (cita.getIdCita() == null) {

            cita.setEstado(EstadoCita.PENDIENTE);
        }
        return citaRepository.save(cita);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    @Transactional
    public void cancelarCita(Long id) {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado(EstadoCita.CANCELADA);
            citaRepository.save(cita);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findByFechaHoraBetween(inicio, fin);
    }
}
