package com.patitasfelices.patitasfelices.service.impl;

import com.patitasfelices.patitasfelices.model.entity.Propietario;
import com.patitasfelices.patitasfelices.repository.PropietarioRepository;
import com.patitasfelices.patitasfelices.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> listarTodos() {
        return propietarioRepository.findAll();
    }

    @Override
    @Transactional
    public Propietario guardar(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Propietario> buscarPorId(Long id) {
        return propietarioRepository.findById(id);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        propietarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> buscarPorNombre(String criterio) {
        return propietarioRepository.findByPersona_NombresContainingIgnoreCase(criterio);
    }
}
