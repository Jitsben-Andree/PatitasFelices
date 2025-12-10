package com.patitasfelices.patitasfelices.service.impl;

import com.patitasfelices.patitasfelices.model.entity.Mascota;
import com.patitasfelices.patitasfelices.repository.MascotaRepository;
import com.patitasfelices.patitasfelices.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    @Override
    @Transactional
    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mascota> buscarPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> buscarPorPropietario(Long idPropietario) {
        return mascotaRepository.findByPropietario_IdPropietario(idPropietario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
